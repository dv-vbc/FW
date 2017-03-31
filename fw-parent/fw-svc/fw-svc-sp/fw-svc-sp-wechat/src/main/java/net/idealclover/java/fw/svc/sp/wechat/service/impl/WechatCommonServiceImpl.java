/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.svc.sp.wechat.service.impl;

import java.util.Arrays;
import net.idealclover.java.fw.svc.sp.wechat.service.IWechatCommonService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author DragonFly
 */
@Service("wechatCommonService")
public class WechatCommonServiceImpl implements IWechatCommonService {
    
    private static final Logger LOG = Logger.getLogger(WechatCommonServiceImpl.class);
    
    @Override
    public Boolean checkServer(String signature, String token, String timestamp, String nonce) {
        Boolean result = false;
        String[] codeArr = new String[]{token, timestamp, nonce};
        // 按字典顺序排序  
        Arrays.sort(codeArr);
        // 字符串拼接 
        StringBuilder codeSb = new StringBuilder();
        for (String code : codeArr) {
            codeSb.append(code);
        }
        String decode = DigestUtils.sha1Hex(codeSb.toString());
        if (StringUtils.equals(signature, decode)) {
            result = true;
        }
        return result;
    }
    
}
