/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.support.svnkit;

/**
 *
 * @author DragonFly
 */
public class Example {

    String account = "lilongfei";
    String password = "lilongfei";
//    String repoPath = "https://58.58.27.124/svn/esc";
    String repoPath = "https://58.58.27.124/svn/esc/trunk/00.ds";
    String workPath = "D:\\localsvn\\58.58.27.124\\esc\\trunk\\00.ds";

    public static void main(String[] args) {
        Example ex = new Example();
        ex.doLogon();
    }
    
    protected void doLogon() {
        try {
            SVNManager svnmanager = new SVNManager();
            svnmanager.logon(repoPath);
            System.out.println(svnmanager.getEntry("", -1));
            System.out.println(svnmanager.listEntry("", -1));
            System.out.println(svnmanager.listEntry("", -1, 4));
            System.out.println(svnmanager.deleteEntry("222", -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
