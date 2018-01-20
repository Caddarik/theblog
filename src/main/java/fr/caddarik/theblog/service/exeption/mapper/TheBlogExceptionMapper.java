/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.exeption.mapper;

import fr.caddarik.theblog.service.exeption.TheBlogException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * Mapper to build the response in case of error
 *
 * @author cedric
 */
@Provider
@Slf4j
public class TheBlogExceptionMapper implements ExceptionMapper<TheBlogException> {

    @Override
    public Response toResponse(TheBlogException exception) {
        String msg = exception.getClass().getName() + " ; " + exception.getMessage() + " ; " + exception.getErrorCode();
        Status status = exception.getStatus();
        log.debug("toResponse() msg = {}, status = {}", msg, status);
        return Response.status(status)
                .entity(msg)
                .build();
    }
    
}
