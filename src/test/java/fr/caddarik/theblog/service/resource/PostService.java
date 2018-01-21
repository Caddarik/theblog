/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.resource;

import fr.caddarik.theblog.model.Post;
import fr.caddarik.theblog.service.exeption.AuthException;
import fr.caddarik.theblog.service.exeption.BadRequestException;
import fr.caddarik.theblog.service.exeption.LoginException;
import fr.caddarik.theblog.service.exeption.ResourceNotFoundException;
import fr.caddarik.theblog.service.exeption.ResourcePersistanceException;
import java.util.List;
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
import org.jboss.resteasy.annotations.cache.Cache;

/**
 * TODO inheritance
 *
 * @author cedric
 */
@Path("post")
public interface PostService {


    /**
     * A service to create a post
     * 
     * @param post the post to create
     * @param login the login/email of the client
     * @param secret the client password crypted with PBKDF2WithHmacSHA1 algorithm
     * @return the id of the created post
     * @throws ResourcePersistanceException If the post could not be created
     * @throws LoginException if the login or password are incorrect
     * @throws AuthException if the client is not allowed de call this service
     */
    @POST
    @Consumes(APPLICATION_XML)
    @NotNull
    public Integer create(@NotNull @Valid Post post,
            @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret)
            throws ResourcePersistanceException, LoginException, AuthException ;

    /**
     * a service to edit/update a post
     * 
     * @param id the id of the post to edit/update
     * @param post the post to edit/update
     * @param login the login/email of the client
     * @param secret the client password crypted with PBKDF2WithHmacSHA1 algorithm
     * @throws ResourcePersistanceException if the post could not be updated
     * @throws ResourceNotFoundException if no user with the corresponding id has been found
     * @throws LoginException if the login or password are incorrect
     * @throws AuthException if the client is not allowed de call this service
     * @throws BadRequestException if the id is different from post.id
     */
    @PUT
    @Path("{id}")
    @Consumes(APPLICATION_XML)
    public void edit(@NotNull @PathParam("id") Integer id, @NotNull @Valid Post post,
            @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret)
            throws ResourcePersistanceException, ResourceNotFoundException, LoginException, AuthException, BadRequestException ;

    /**
     * a service to delete/remove a post
     * @param id the id of the post to remove
     * @param login the login/email of the client
     * @param secret the client password crypted with PBKDF2WithHmacSHA1 algorithm
     * @throws ResourcePersistanceException if the post could not be deleted
     * @throws ResourceNotFoundException if no user with the corresponding id has been found
     * @throws LoginException if the login or password are incorrect
     * @throws AuthException if the client is not allowed de call this service
     */
    @DELETE
    @Path("{id}")
    public void remove(@NotNull @PathParam("id") Integer id,
            @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret)
            throws ResourcePersistanceException, ResourceNotFoundException, LoginException, AuthException ;

    /**
     * a service to find a post by the id
     * 
     * @param id the id of the post to find
     * @return the corresonding post
     * @throws ResourceNotFoundException if no user with the corresponding id has been found
     */
    @Cache(sMaxAge = 5*60)
    @GET
    @Path("{id}")
    @Produces(APPLICATION_XML)
    public Post find(@NotNull @PathParam("id") Integer id) throws ResourceNotFoundException ;

    /**
     * A service to find posts with a keyword
     * 
     * @param keyword the keyword to find in the title or the body
     * @return a List of the matching posts
     */
    @Cache(sMaxAge = 5*60)
    @GET
    @Produces(APPLICATION_XML)
    public List<Post> find(@NotNull @QueryParam("keyword") String keyword) ;

}
