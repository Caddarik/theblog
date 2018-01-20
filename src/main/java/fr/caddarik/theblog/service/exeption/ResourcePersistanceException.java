/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 *
 * @author cedric
 */
public class ResourcePersistanceException extends TheBlogException {


    /**
     * Constructs an instance of <code>PersistenceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ResourcePersistanceException(String msg) {
        super(INTERNAL_SERVER_ERROR, "4", msg);
    }
    
    /**
     * Constructs an instance of <code>PersistenceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param cause the cause of the exeption
     */
    public ResourcePersistanceException(String msg, Throwable cause) {
        super(INTERNAL_SERVER_ERROR, "4", msg, cause);
    }
    
    
}
