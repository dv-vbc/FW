/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.web.demo.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.idealclover.java.fw.svc.sp.wechat.service.IWechatCommonService;
import net.idealclover.java.fw.web.com.core.exception.WebRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DragonFly
 */
@Controller
@RequestMapping("/demo/wechat")
public class WechatController {

    private static final Logger LOG = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private IWechatCommonService wechatCommonService;

    @RequestMapping(value = "/checkServer.html", method = RequestMethod.GET)
    public void checkServer(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String token = "jnnnn";
        String infomsg = "楠岃瘉寰俊鏈嶅姟鍣ㄥけ璐�, {signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + "}";
        LOG.info("FW-51002: " + infomsg);
        Boolean checkResult = wechatCommonService.checkServer(signature, token, timestamp, nonce);
        if (checkResult) {
            renderData(response, echostr);
            LOG.info("楠岃瘉寰俊鏈嶅姟鍣ㄦ垚鍔�");
        } else {
            String errmsg = "楠岃瘉寰俊鏈嶅姟鍣ㄥけ璐�, {signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + "}";
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
            String errmsg = "杈撳嚭椤甸潰鍝嶅簲淇℃伅澶辫触";
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
