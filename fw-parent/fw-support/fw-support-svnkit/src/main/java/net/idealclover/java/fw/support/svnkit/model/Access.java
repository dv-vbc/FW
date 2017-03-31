/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.support.svnkit.model;

/**
 *
 * @author DragonFly
 */
public class Access extends Account {

    private static final long serialVersionUID = 1L;
    private String repoPath;
    private String workPath;

    public Access(String repoPath, String workPath, String account, String password) {
        super(account, password);
        this.repoPath = repoPath;
        this.workPath = workPath;
    }

    public String getRepoPath() {
        return repoPath;
    }

    public void setRepoPath(String repoPath) {
        this.repoPath = repoPath;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

}
