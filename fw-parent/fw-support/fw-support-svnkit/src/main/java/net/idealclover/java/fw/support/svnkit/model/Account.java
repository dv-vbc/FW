/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.support.svnkit.model;

import java.io.Serializable;

/**
 *
 * @author DragonFly
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private String account;// svn账号
    private String password;// svn密码

    protected Account() {

    }

    public Account(String account, String password) {
        super();
        this.account = account;
        this.password = password;
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
