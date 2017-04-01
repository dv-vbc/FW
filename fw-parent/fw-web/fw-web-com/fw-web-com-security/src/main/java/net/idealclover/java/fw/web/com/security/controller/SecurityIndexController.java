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

/**
 *
 * @author DragonFly
 */
@Controller
@RequestMapping("")
public class SecurityIndexController {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityIndexController.class);

    /**
     * 管理员和普通用户可以访问
     */
    @RequestMapping(value = "/index")
    public String index() {
        LOG.info("index.......");
        return "/com/security/views/index";
    }

    /**
     * 管理员和普通用户可以访问
     */
    @RequestMapping(value = "/common")
    public String myJsp() {
        LOG.info("common.......");
        return "/com/security/views/common";
    }

    /**
     * 管理员可以访问
     */
    @RequestMapping(value = "/admin")
    public String admin() {
        LOG.info("admin.......");
        return "/com/security/views/admin";
    }
}
