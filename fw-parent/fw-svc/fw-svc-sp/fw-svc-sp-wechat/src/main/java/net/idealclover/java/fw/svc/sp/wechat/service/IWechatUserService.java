/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.svc.sp.wechat.service;

import java.lang.reflect.InvocationTargetException;
import net.idealclover.java.fw.vo.sp.wechat.UserVo;

/**
 *
 * @author DragonFly
 */
public interface IWechatUserService {
    public UserVo getUser() throws Exception;
    public Integer addUser(String name) throws Exception;
}
