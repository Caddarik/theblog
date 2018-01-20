/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import fr.caddarik.theblog.model.User;
import fr.caddarik.theblog.security.AuthorizationChecker;
import fr.caddarik.theblog.service.exeption.LoginException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cedric
 */
@Slf4j
@Stateless
@Path("security")
public class SecurityService {

    @Inject
    private AuthorizationChecker authorizationChecker;

    /**
     * return the user corresponding to this login and password
     * 
     * @param login the email of the user
     * @param secret the password crypted with PBKDF2WithHmacSHA1 algorithm
     * @return the corresponding user
     * @throws LoginException if login or password is incorrect
     */
    @GET
    @Path("login")
    @Produces(APPLICATION_XML)
    public User login(@NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret) throws LoginException {
        try {
            log.debug("login({},{})", login, secret);
            return authorizationChecker.login(login, secret);
        } catch(LoginException le) {
            throw le;
        } catch (RuntimeException e) {
            log.error("login({},{})  raise an Exception", login, secret, e);
            throw e;
        }
    }
}
