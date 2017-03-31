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
public class User {

    private Integer id;
    /**
     * 帐号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

    @Override
    public int hashCode() {
        return account.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return this.account.equals(user.getAccount());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
