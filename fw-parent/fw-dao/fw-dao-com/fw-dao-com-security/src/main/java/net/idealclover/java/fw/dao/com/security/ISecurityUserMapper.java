/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.dao.com.security;

import net.idealclover.java.fw.po.com.security.User;

/**
 *
 * @author DragonFly
 */
public interface ISecurityUserMapper {

    /**
     * 根据帐号查询用户
     *
     * @param account 帐号
     * @return
     */
    User selectByAccount(String account);
}
