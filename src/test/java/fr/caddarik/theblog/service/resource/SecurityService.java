/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.resource;

import fr.caddarik.theblog.model.User;
import fr.caddarik.theblog.service.exeption.LoginException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * 
 * @author cedric
 */
@Path("security")
public interface SecurityService {

    @GET
    @Path("login")
    @Produces(APPLICATION_XML)
    public User login(@NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret) throws LoginException ;
    
}
