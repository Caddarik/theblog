/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import fr.caddarik.theblog.dao.PostDAO;
import fr.caddarik.theblog.model.Post;
import fr.caddarik.theblog.security.AuthorizationChecker;
import fr.caddarik.theblog.service.exeption.ResourceNotFoundException;
import fr.caddarik.theblog.service.exeption.ResourcePersistanceException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO inheritance
 *
 * @author cedric
 */
@Stateless
@Path("post")
@Slf4j
public class PostService {

    @Inject
    private PostDAO dao;
    
    @Inject
    private AuthorizationChecker authorizationChecker;

    @POST
    @Consumes(APPLICATION_XML)
    public Integer create(@NotNull @Valid Post post,
            @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret)
            throws ResourcePersistanceException {
        
        try {
            log.debug("create({})", post);
            
            // Auth
            
            return dao.create(post);
        } catch (RuntimeException e) {
            log.error("create({}) raise an Exception", post, e);
            throw e;
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(APPLICATION_XML)
    public void edit(@NotNull @PathParam("id") Integer id, @NotNull @Valid Post post) throws ResourcePersistanceException, ResourceNotFoundException {
        try {
            log.debug("edit({}, {})", id, post);
            
            // Auth
            
            dao.update(post);
        } catch (RuntimeException e) {
            log.error("edit({}, {}) raise an Exception", id, post, e);
            throw e;
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@NotNull @PathParam("id") Integer id) throws ResourcePersistanceException, ResourceNotFoundException {
        try {
            log.debug("remove({})", id);
            
            // Auth
            
            dao.delete(id);
        } catch (RuntimeException e) {
            log.error("remove({}) raise an Exception", id, e);
            throw e;
        }

    }

    @GET
    @Path("{id}")
    @Produces(APPLICATION_XML)
    public Post find(@NotNull @PathParam("id") Integer id) throws ResourceNotFoundException {
        try {
            log.debug("find({})", id);
            return dao.find(id);
        } catch (RuntimeException e) {
            log.error("find({}) raise and Exception", id, e);
            throw e;
        }
    }

    @GET
    @Produces(APPLICATION_XML)
    public List<Post> find(@NotNull @QueryParam("keyword") String keyword) {
        try {
            log.debug("find({})", keyword);
            return dao.search(keyword);
        } catch (RuntimeException e) {
            log.error("find({}) raise and Exception", keyword, e);
            throw e;
        }
    }

}
