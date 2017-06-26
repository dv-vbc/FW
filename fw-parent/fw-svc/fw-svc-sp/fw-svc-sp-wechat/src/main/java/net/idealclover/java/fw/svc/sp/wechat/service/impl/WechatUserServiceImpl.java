/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.svc.sp.wechat.service.impl;

import net.idealclover.java.fw.dao.sp.wechat.IWechatUserMapper;
import net.idealclover.java.fw.po.sp.wechat.User;
import net.idealclover.java.fw.svc.sp.wechat.service.IWechatUserService;
import net.idealclover.java.fw.vo.sp.wechat.UserVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DragonFly
 */
@Service("wechatUserService")
public class WechatUserServiceImpl implements IWechatUserService {

    @Autowired
    private IWechatUserMapper wechatUserMapper;

    @Override
    public UserVo getUser() throws Exception {
        User user = wechatUserMapper.getUser(1);
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(vo, user);
        return vo;
    }

    @Override
    public Integer addUser(String name) throws Exception {
        return wechatUserMapper.addUser(name);
    }

}
