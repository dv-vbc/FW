/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.fx.esckit.service.impl;

import net.idealclover.java.fw.fx.esckit.dao.IPSysparaMapper;
import net.idealclover.java.fw.fx.esckit.po.PSyspara;
import net.idealclover.java.fw.fx.esckit.service.ITransService;
import net.idealclover.java.fw.fx.esckit.vo.SysparaVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DragonFly
 */
@Service("transService")
public class TransServiceImpl implements ITransService {

    @Autowired
    private IPSysparaMapper dao;

    @Override
    public SysparaVo getWebState() throws Exception {

        PSyspara model = dao.findByCode("SYS_PARA_WEBSTATE");

        SysparaVo vo = new SysparaVo();

        String name = model.getName();
        String[] nameArr = StringUtils.split(name, "$");

        vo.setCode(nameArr[0]);
        vo.setName(nameArr[1]);
        vo.setValue(model.getDefaultValue());

        return vo;
    }

}
