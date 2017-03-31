/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.web.com.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author DragonFly
 */
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static Logger LOG = LoggerFactory.getLogger(SecurityAuthenticationSuccessHandler.class);

    //登录验证成功后需要跳转的url
    private String url;

    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        LOG.info("登录验证成功：" + request.getContextPath() + url);
        //response.sendRedirect(request.getContextPath()+url);
        request.getRequestDispatcher(url).forward(request, response);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
