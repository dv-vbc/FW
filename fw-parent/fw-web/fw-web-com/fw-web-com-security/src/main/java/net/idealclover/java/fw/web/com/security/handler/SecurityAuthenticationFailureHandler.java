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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 *
 * @author DragonFly
 */
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler  {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException ae)
            throws IOException, ServletException {
        
        //根据AuthenticationException异常的类型
        //进行出错业务逻辑处理
        //...
        
        response.sendRedirect(request.getContextPath()+"/login");
    }
}
