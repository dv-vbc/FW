/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.web.kb.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.idealclover.java.fw.svc.sp.wechat.service.IWechatCommonService;
import net.idealclover.java.fw.web.com.core.exception.WebRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author DragonFly
 */
@Controller
@RequestMapping("/kb/wechat/common")
public class WechatCommonController {

    private static final Logger LOG = Logger.getLogger(WechatCommonController.class);

    @Autowired
    private IWechatCommonService wechatCommonService;

    @RequestMapping(value = "/checkServer.html", method = RequestMethod.GET)
    public void checkServer(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String token = "jnnnn";
        Boolean checkResult = wechatCommonService.checkServer(signature, token, timestamp, nonce);
        if (checkResult) {
            renderData(response, echostr);
            LOG.info("验证微信服务器成功");
        } else {
            String errmsg = "验证微信服务器失败, {signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + "}";
            LOG.error("FW-51002: " + errmsg);
            throw new WebRuntimeException("FW-51002", errmsg);
        }
    }

    private void renderData(HttpServletResponse response, String data) {
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(data);
        } catch (IOException ex) {
            String errmsg = "输出页面响应信息失败";
            LOG.error("FW-51001: " + errmsg, ex);
            throw new WebRuntimeException("FW-51001", errmsg, ex);
        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
