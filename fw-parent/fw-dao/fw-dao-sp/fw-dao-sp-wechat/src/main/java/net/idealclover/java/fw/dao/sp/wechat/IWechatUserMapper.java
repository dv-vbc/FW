/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.dao.sp.wechat;

import net.idealclover.java.fw.po.sp.wechat.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DragonFly
 */
@Repository("wechatUserMapper")
public interface IWechatUserMapper {

    Integer addUser(String name);

    User getUser(Integer id);

//    Integer create(User user);
//	Integer delete(Integer id);
//	Integer modify(User user);
//	
//	User findById(Integer id);
//	User findByUser(User user);
//	List<User> findAll();
//	List<User> findAll(User user);
//	
//	Integer count();
}
