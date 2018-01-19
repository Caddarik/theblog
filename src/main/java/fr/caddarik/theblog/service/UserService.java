/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import fr.caddarik.theblog.dao.UserDAO;
import fr.caddarik.theblog.model.User;
import fr.caddarik.theblog.security.AuthorizationChecker;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cedric
 */
@Stateless
@Path("user")
@Slf4j
public class UserService {

    @Inject
    private UserDAO dao;

    @Inject
    private AuthorizationChecker authorizationChecker;
    
    public UserService() {
    }

    @POST
    @Consumes(APPLICATION_XML)
    public Integer create(User entity) {
        try {
            log.debug("create({})", entity);
            return dao.create(entity);
        } catch (RuntimeException e) {
            log.error("create({}) raise an Exception", entity, e);
            throw e;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(APPLICATION_XML)
    public void edit(@PathParam("id") Integer id, User user, @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret) throws IllegalArgumentException, AuthException, LoginException {
        try {
            log.debug("edit({}, {})", id, user);
            if(!Objects.equals(id, user.getId())) {
                throw new IllegalArgumentException("the PathParam id must be the same as user.getId()");
            }
            authorizationChecker.checkForUser(login, secret, id);
            dao.update(user);
        } catch (RuntimeException e) {
            log.error("edit({}, {}) raise an Exception", id, user, e);
            throw e;
        }
    }

    @GET
    @Path("{id}")
    @Produces(APPLICATION_XML)
    public User find(@PathParam("id") Integer id, @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret) throws AuthException, LoginException {
        try {
            log.debug("find({})", id);
            authorizationChecker.checkForUser(login, secret, id);
            return dao.find(id);
        } catch (RuntimeException e) {
            log.error("find({}) raise an Exception", id, e);
            throw e;
        }
    }

}
