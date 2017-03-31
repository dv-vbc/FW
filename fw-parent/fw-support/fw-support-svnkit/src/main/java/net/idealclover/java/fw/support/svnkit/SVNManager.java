/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.support.svnkit;

import org.apache.log4j.Logger;

/**
 *
 * @author DragonFly
 */
public class SVNManager extends AbstractSVNManager {

    private static final Logger LOG = Logger.getLogger(SVNManager.class);

    public void logon(String url) {
        String username = "lilongfei";
        String password = "lilongfei";
        this.logon(url, username, password);
    }

}
