/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.web.com.core.exception;

/**
 *
 * @author DragonFly
 */
public class WebRuntimeException extends RuntimeException {

    private String expcode;  //异常对应的返回码
    private String expmsg;  //异常对应的描述信息

    public WebRuntimeException() {
        super();
    }

    public WebRuntimeException(String message) {
        super(message);
        expmsg = message;
    }

    public WebRuntimeException(String code, String message) {
        super(code + ": " + message);
        this.expcode = code;
        this.expmsg = message;
    }

    public WebRuntimeException(String code, String message, Throwable e) {
        super(code + ": " + message + ", " + e.getMessage(), e);
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
