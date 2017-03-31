/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.svc.com.security.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.idealclover.java.fw.dao.com.security.ISecurityRoleMapper;
import net.idealclover.java.fw.dao.com.security.ISecurityUserMapper;
import net.idealclover.java.fw.po.com.security.Role;
import net.idealclover.java.fw.po.com.security.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.idealclover.java.fw.svc.com.security.ISecurityLoginService;

/**
 *
 * @author DragonFly
 */
public class SecurityLoginServiceImpl implements ISecurityLoginService {

    private static Logger log = LoggerFactory.getLogger(SecurityLoginServiceImpl.class);

    @Autowired
    private ISecurityUserMapper userMapper;

    @Autowired
    private ISecurityRoleMapper roleMapper;

    /**
     * @param account 登录帐号
     */
    public UserDetails loadUserByUsername(String account)
            throws UsernameNotFoundException {
        log.info("登录账号：" + account);
        org.springframework.security.core.userdetails.User userDetails = null;
        User user = userMapper.selectByAccount(account);

        //账号密码错误，可以在这里手动抛出异常，让验证失败处理器AuthenticationFailureHandler进行处理
        Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
        boolean enables = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        userDetails = new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return userDetails;
    }

    /**
     * 根据用户获取该用户拥有的角色
     *
     * @param user
     * @return
     */
    private Set<GrantedAuthority> getGrantedAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        List<Role> roles = roleMapper.selectByUserId(user.getId());
        if (roles != null) {
            for (Role role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return grantedAuthorities;
    }
}
