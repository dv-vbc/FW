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
public interface IRepositoryManager {
    public abstract void logon(String url, String username, String password);
    public abstract String getEntry(String path, long revision);
    public abstract String listEntry(String path, long revision);
    public abstract String listEntry(String path, long revision, int depth);
    public abstract String deleteEntry(String path, long revision);
    public abstract String deleteEntry(String path, long revision, String comment);
}
