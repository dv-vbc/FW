/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.support.svnkit.exception;

/**
 *
 * @author DragonFly
 */
public class SVNKitSupportException extends Exception {

    private String expcode;  //异常对应的返回码
    private String expmsg;  //异常对应的描述信息

    public SVNKitSupportException() {
        super();
    }

    public SVNKitSupportException(String message) {
        super(message);
        expmsg = message;
    }

    public SVNKitSupportException(String code, String message) {
        super(code + ":" + message);
        this.expcode = code;
        this.expmsg = message;
    }

    public String getExpcode() {
        return expcode;
    }

    public String getExpmsg() {
        return expmsg;
    }
    
}
