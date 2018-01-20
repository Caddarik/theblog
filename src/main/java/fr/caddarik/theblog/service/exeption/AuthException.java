/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 *
 * @author cedric
 */
public class AuthException extends TheBlogException {

    /**
     * Constructs an instance of <code>AuthException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AuthException(String msg) {
        super(UNAUTHORIZED, "2", msg);
    }
}
