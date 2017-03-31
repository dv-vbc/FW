/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.web.kb.wechat.controller;

import java.util.logging.Level;
import net.idealclover.java.fw.svc.sp.wechat.service.IWechatCommonService;
import net.idealclover.java.fw.svc.sp.wechat.service.IWechatUserService;
import net.idealclover.java.fw.vo.sp.wechat.UserVo;
import org.apache.commons.beanutils.BeanUtils;
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
@RequestMapping("/kb/wechat/setup")
public class WechatSetupController {
    
    private static final Logger LOG = Logger.getLogger(WechatSetupController.class);
    
    @Autowired
    private IWechatUserService wechatUserService;
    
    @RequestMapping(value = "/main.html",method = RequestMethod.GET)
    public String getMain() {
        try {
            wechatUserService.addUser("某个人");
            UserVo vo = wechatUserService.getUser();
            System.out.println(vo);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(WechatSetupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "/kb/wechat/views/setup/main";
    }
    
    @RequestMapping(value = "/fb.html",method = RequestMethod.GET)
    public String getFb() {
        
        return "/kb/wechat/views/setup/fb";
    }
    
    @RequestMapping(value = "/avalon01.html",method = RequestMethod.GET)
    public String getAvalon01() {
        
        return "/kb/wechat/views/setup/avalon01";
    }
    
    @RequestMapping(value = "/avalon02.html",method = RequestMethod.GET)
    public String getAvalon02() {
        
        return "/kb/wechat/views/setup/avalon02";
    }
    
    @RequestMapping(value = "/avalon03.html",method = RequestMethod.GET)
    public String getAvalon03() {
        
        return "/kb/wechat/views/setup/avalon03";
    }
}
