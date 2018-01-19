/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption;

import javax.security.auth.login.LoginException;
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
public class LoginExceptionMapper implements ExceptionMapper<LoginException> {

    @Override
    public Response toResponse(LoginException exception) {
        String msg = "LoginException : " + exception.getMessage();
        log.debug("toResponse() msg = {}", msg);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(msg)
                .build();
    }
    
}
