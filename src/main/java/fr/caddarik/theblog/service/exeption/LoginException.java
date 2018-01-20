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
public class LoginException extends TheBlogException {

    public LoginException(String msg) {
        super(NOT_FOUND, "1", msg);
    }

}
