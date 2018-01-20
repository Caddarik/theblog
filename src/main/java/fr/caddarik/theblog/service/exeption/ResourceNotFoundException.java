/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 *
 * @author cedric
 */
public class ResourceNotFoundException extends TheBlogException {

    /**
     * Constructs an instance of <code>ResourceNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ResourceNotFoundException(String msg) {
        super(NOT_FOUND, "5", msg);
    }

    /**
     * Constructs an instance of <code>ResourceNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param cause the cause
     */
    public ResourceNotFoundException(String msg, Throwable cause) {
        super(NOT_FOUND, "5", msg, cause);
    }
}
