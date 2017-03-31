/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.web.com.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DragonFly
 */
@Controller
@RequestMapping("/com/security/login")
public class SecurityLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityLoginController.class);
    
    /**
     * 登录入口
     */
    @RequestMapping(value={"/","/login"})
    public String login(@RequestParam(required=false) String error) {
        LOG.info("login......");
        if("1".equals(error)) {
            LOG.info("验证失败！");
        }else if("2".equals(error)) {
            LOG.info("你的帐号已登录，不允许重复登陆！");
        }else if("3".equals(error)) {
            LOG.info("会话超时!");
        }
        return "login";
    }
    
    /**
     * 没有权限访问跳转url
     */
    @RequestMapping(value="/denied")
    public String denied(){
        LOG.info("denied......");
        return "denied";
    }
    
    /**
     * 超时跳转url
     */
    @RequestMapping(value="/timeout")
    public String timedout(){
        LOG.info("timeout......");
        return "timedout";
    }
}
