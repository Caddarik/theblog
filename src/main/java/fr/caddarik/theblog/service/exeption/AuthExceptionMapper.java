/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import javax.security.auth.message.AuthException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cedric
 */
@Provider
@Slf4j
public class AuthExceptionMapper implements ExceptionMapper<AuthException> {

    @Override
    public Response toResponse(AuthException exception) {
        String msg = "AuthException : " + exception.getMessage();
        log.debug("toResponse() msg = {}", msg);
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(msg)
                .build();
    }
    
}
