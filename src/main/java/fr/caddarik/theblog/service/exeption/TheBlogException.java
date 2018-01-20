/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import javax.ws.rs.core.Response.Status;

/**
 *
 * @author cedric
 */
public class TheBlogException extends Exception {

    private final Status status;
    
    private final String errorCode;

    /**
     * Constructs an instance of <code>TheBlogException</code> with the
     * specified detail message, status and error code
     *
     * @param status the HTTP status
     * @param errorCode
     * @param msg the detail message.
     */
    public TheBlogException(Status status, String errorCode, String msg) {
        super(msg);
        this.status = status;
        this.errorCode = errorCode;
    }

    /**
     * Constructs an instance of <code>TheBlogException</code> with the
     * specified detail message, status, error code and cause
     * 
     * @param status
     * @param errorCode
     * @param message
     * @param cause
     */
    public TheBlogException(Status status, String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = errorCode;
    }

    
    
    public Status getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }
    
    
}
