/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.support.svnkit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Collection;
import java.util.Iterator;
import net.idealclover.java.fw.support.svnkit.exception.SVNKitSupportRuntimeException;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 *
 * @author DragonFly
 */
public abstract class AbstractSVNManager implements IRepositoryManager {

    private static final Logger LOG = Logger.getLogger(AbstractSVNManager.class);
    protected SVNRepository svnRepository = null;
    protected String root = null;

    /**
     * 设置登录SVN资源库
     *
     * @param url
     * @param username
     * @param password
     */
    @Override
    public void logon(String url, String username, String password) {
        setup(url);
        try {
            svnRepository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        } catch (SVNException svne) {
            LOG.error("FW-31002: SVN资源库URL[" + url + "]解析失败", svne);
            throw new SVNKitSupportRuntimeException("FW-31002", "SVN资源库URL[" + url + "]解析失败", svne);
        }
        this.root = url;
        char[] pwd = password.toCharArray();
        ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(username, pwd);
        svnRepository.setAuthenticationManager(authenticationManager);
        LOG.info("访问SVN资源库设置完成");
    }

    /**
     * 展示对象信息
     *
     * @param path
     * @param revision
     * @return
     */
    @Override
    public String getEntry(String path, long revision) {
        return listEntry(path, revision, 0);
    }

    /**
     * 展示对象树信息
     *
     * @param path
     * @param revision
     * @return
     */
    @Override
    public String listEntry(String path, long revision) {
        return listEntry(path, revision, -1);
    }

    /**
     * 展示固定深度对象树信息
     *
     * @param path
     * @param revision -1为最新版本
     * @param depth 深度为0时仅显示path的节点，随深度增加依次加深，-1不限制深度
     * @return
     */
    @Override
    public String listEntry(String path, long revision, int depth) {
        String result = null;
        SVNNodeKind svnNodeKind = null;
        try {
            svnNodeKind = svnRepository.checkPath(path, revision);
        } catch (SVNException svne) {
            LOG.error("FW-31003: 路径[" + root + '/' + path + "]访问异常", svne);
            throw new SVNKitSupportRuntimeException("FW-31003", "路径[" + root + '/' + path + "]访问异常", svne);
        }
        if (svnNodeKind == SVNNodeKind.DIR) {
            try {
                result = printDirRecursion(path, revision, depth);
            } catch (SVNException svne) {
                LOG.error("FW-31004: 路径[" + root + '/' + path + "]的目录访问异常", svne);
                throw new SVNKitSupportRuntimeException("FW-31004", "路径[" + root + '/' + path + "]的目录访问异常", svne);
            }
        } else if (svnNodeKind == SVNNodeKind.FILE) {
            try {
                result = printDirRecursion(path, revision, 0);
            } catch (SVNException svne) {
                LOG.error("FW-31005: 路径[" + root + '/' + path + "]的文件访问异常", svne);
                throw new SVNKitSupportRuntimeException("FW-31005", "路径[" + root + '/' + path + "]的文件访问异常", svne);
            }
        } else if (svnNodeKind == SVNNodeKind.NONE) {
            LOG.error("FW-31006: 路径[" + root + '/' + path + "]的对象不存在");
            throw new SVNKitSupportRuntimeException("FW-31006", "路径[" + root + '/' + path + "]的对象不存在");
        } else if (svnNodeKind == SVNNodeKind.UNKNOWN) {
            LOG.error("FW-31007: 路径[" + root + '/' + path + "]的对象无法识别");
            throw new SVNKitSupportRuntimeException("FW-31007", "路径[" + root + '/' + path + "]的对象无法识别");
        }
        return result;
    }

    /**
     * 删除对象（树）
     *
     * @param path
     * @param revision
     * @return
     */
    @Override
    public String deleteEntry(String path, long revision) {
        return this.deleteEntry(path, revision, "delete file");
    }

    /**
     * 删除对象（树）
     *
     * @param path
     * @param revision
     * @param comment
     * @return
     */
    @Override
    public String deleteEntry(String path, long revision, String comment) {
        ISVNEditor editor = null;
        SVNCommitInfo cinfo;
        try {
            editor = svnRepository.getCommitEditor(comment, null, true, null);
        } catch (SVNException svne) {
            LOG.error("FW-31008: 获取SVN编辑器对象异常", svne);
            throw new SVNKitSupportRuntimeException("FW-31008", "获取SVN编辑器对象异常", svne);
        }
        try {
            // 获取编辑器
            // 进入Root节点，即logon时设置的url路径
            editor.openRoot(revision);
            // 删除文件
            editor.deleteEntry(path, revision);
            // 操作完成要关闭编辑器，并返回操作结果
            cinfo = editor.closeEdit();
            return JSON.toJSONString(parseJSON4SVNCommitInfo(cinfo));
        } catch (SVNException svne) {
            try {
                //发生异常需要终止操作
                editor.abortEdit();
            } catch (SVNException svne1) {
                LOG.error("FW-31009: 中止SVN编辑器操作异常", svne1);
                throw new SVNKitSupportRuntimeException("FW-31009", "中止SVN编辑器操作异常", svne1);
            }
            LOG.error("FW-31010: 路径[" + root + '/' + path + "]的对象删除异常", svne);
            throw new SVNKitSupportRuntimeException("FW-31010", "路径[" + root + '/' + path + "]的对象删除异常", svne);
        }
    }

    /**
     * 根据url协议设施SVNRepositoryFactory
     *
     * @param url
     */
    private void setup(String url) {
        String[] schemes = {"http", "https", "svn", "svns", "file"};
        UrlValidator urlv = new UrlValidator(schemes, UrlValidator.ALLOW_2_SLASHES | UrlValidator.ALLOW_ALL_SCHEMES | UrlValidator.ALLOW_LOCAL_URLS);
        if (!urlv.isValid(url)) {
            LOG.error("FW-31001: SVN资源库URL[" + url + "]不合法");
            throw new SVNKitSupportRuntimeException("FW-31001", "SVN资源库URL[" + url + "]不合法");
        }

        if (url.startsWith("http") || url.startsWith("https")) {
            DAVRepositoryFactory.setup();
        } else if (url.startsWith("svn") || url.startsWith("svns")) {
            SVNRepositoryFactoryImpl.setup();
        } else if (url.startsWith("file")) {
            FSRepositoryFactory.setup();
        }
    }

    /**
     * 递归打印路径信息
     *
     * @param path
     * @param revision
     * @param depth
     * @return
     * @throws SVNException
     */
    private String printDirRecursion(String path, long revision, int depth) throws SVNException {
        SVNDirEntry svnDirEntry = svnRepository.getDir(path, revision, true, (Collection) null);
        JSONObject jobj = parseJSON4SVNDirEntry(svnDirEntry);
        if ((depth > 0 || depth == -1) && svnDirEntry.getKind() == SVNNodeKind.DIR) {
            Collection<SVNDirEntry> entries = svnRepository.getDir(path, revision, null, (Collection) null);
            Iterator<SVNDirEntry> it = entries.iterator();
            JSONArray jarr = new JSONArray();
            while (it.hasNext()) {
                SVNDirEntry entry = it.next();
                String nextPath = "".equals(path) ? entry.getName() : path + "/" + entry.getName();
                int nextDepth = depth > 0 ? depth - 1 : depth;
                jarr.add(JSON.parseObject(printDirRecursion(nextPath, revision, nextDepth)));
                jobj.put("subnodes", jarr);
            }
        }
        String entriesString = JSON.toJSONString(jobj);
        return entriesString;
    }

    /**
     * SVNDirEntry转JSONObject
     *
     * @param entry
     * @return
     */
    private JSONObject parseJSON4SVNDirEntry(SVNDirEntry entry) {

        JSONObject jobj = new JSONObject();
        jobj.fluentPut("author", entry.getAuthor()).fluentPut("revision", entry.getRevision());
        jobj.fluentPut("path", entry.getURL().toString()).fluentPut("commitMessage", entry.getCommitMessage());
        jobj.fluentPut("date", DateFormatUtils.format(entry.getDate(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        jobj.fluentPut("size", entry.getSize()).fluentPut("kind", entry.getKind().toString());
        jobj.fluentPut("name", entry.getURL().getPath().substring(entry.getURL().getPath().lastIndexOf("/") + 1));

        return jobj;
    }

    /**
     * SVNCommitInfo扎unJSONObject
     *
     * @param entry
     * @return
     */
    private JSONObject parseJSON4SVNCommitInfo(SVNCommitInfo entry) {

        JSONObject jobj = new JSONObject();
        jobj.fluentPut("author", entry.getAuthor()).fluentPut("revision", entry.getNewRevision());
        jobj.fluentPut("errorMessage", entry.getErrorMessage());
        jobj.fluentPut("date", DateFormatUtils.format(entry.getDate(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

        return jobj;
    }
}
