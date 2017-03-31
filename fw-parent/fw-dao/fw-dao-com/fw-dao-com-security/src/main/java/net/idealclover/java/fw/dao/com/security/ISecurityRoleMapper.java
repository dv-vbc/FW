/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.dao.com.security;

import java.util.List;
import net.idealclover.java.fw.po.com.security.Role;

/**
 *
 * @author DragonFly
 */
public interface ISecurityRoleMapper {

    /**
     * 根据用户id获取角色集合
     *
     * @param userId
     * @return
     */
    List<Role> selectByUserId(Integer userId);
}
