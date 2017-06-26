/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.svc.sp.wechat.service;

/**
 *
 * @author DragonFly
 */
public interface IWechatAccessService {
    
    String getAccessToken();
    
    void updateAccessToken();
    
}
