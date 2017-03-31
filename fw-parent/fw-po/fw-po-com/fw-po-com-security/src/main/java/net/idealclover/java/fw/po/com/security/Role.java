/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.po.com.security;

/**
 *
 * @author DragonFly
 */
public class Role {

    private Integer id;
    /**
     * 角色名称
     */
    private String name;

    //getter setter
    //...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
