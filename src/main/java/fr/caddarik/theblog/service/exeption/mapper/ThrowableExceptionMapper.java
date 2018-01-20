/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * Mapper to build the response in case of error
 * https://howtodoinjava.com/resteasy/exception-handling-in-jax-rs-resteasy-with-exceptionmapper/
 *
 * @author cedric
 */
@Provider
@Slf4j
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        String msg = exception.getClass().getName() + " : " + exception.getMessage();
        log.debug("toResponse() msg = {}", msg);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(msg)
                .build();
    }
    
}
