/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 *
 * @author cedric
 */
public class BadRequestException extends TheBlogException {


    /**
     * Constructs an instance of <code>BadRequestException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BadRequestException(String msg) {
        super(BAD_REQUEST, "3", msg);
    }
}
