/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service.resource;

import fr.caddarik.theblog.model.User;
import fr.caddarik.theblog.service.exeption.AuthException;
import fr.caddarik.theblog.service.exeption.BadRequestException;
import fr.caddarik.theblog.service.exeption.LoginException;
import fr.caddarik.theblog.service.exeption.ResourceNotFoundException;
import fr.caddarik.theblog.service.exeption.ResourcePersistanceException;
import javax.validation.Valid;
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
import lombok.NonNull;

/**
 *
 * @author cedric
 */
@Path("user")
public interface UserService {

    /**
     * A service to create a new user
     * 
     * @param user the user to create
     * @return the id of the created user
     * @throws ResourcePersistanceException if the user is not persisted
     */
    @POST
    @Consumes(APPLICATION_XML)
    @NotNull
    public Integer create(@NonNull @Valid User user) throws ResourcePersistanceException ;

    /**
     * A service to edit/update a user
     * 
     * @param id the id of the user to update
     * @param user the user to update
     * @param login the login of the client
     * @param secret the client password crypted with PBKDF2WithHmacSHA1 algorithm
     * @throws BadRequestException if the id is not equals to user.id 
     * @throws AuthException if the client is not allowed to call this method
     * @throws LoginException if the login or password are incorrect
     * @throws ResourcePersistanceException if the entity was not updated
     * @throws ResourceNotFoundException if no user with the corresponding id has been found
     */
    @PUT
    @Path("{id}")
    @Consumes(APPLICATION_XML)
    public void edit(@NotNull @PathParam("id") Integer id, @NotNull @Valid User user,
            @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret)
            throws BadRequestException, AuthException, LoginException, ResourcePersistanceException, ResourceNotFoundException ;

    /**
     * A service to find a user by ID
     * 
     * @param id the Id of the user to find
     * @param login the login of the client
     * @param secret the client password crypted with PBKDF2WithHmacSHA1 algorithm
     * @return the corresponding user
     * @throws AuthException if the client is not allowed to call this method
     * @throws LoginException if the login or password are incorrect
     * @throws ResourceNotFoundException if no user with the corresponding id has been found
     */
    @GET
    @Path("{id}")
    @Produces(APPLICATION_XML)
    @Valid
    public User find(@NotNull @PathParam("id") Integer id,
            @NotNull @HeaderParam("login") String login, @NotNull @HeaderParam("secret") String secret)
            throws AuthException, LoginException, ResourceNotFoundException ;

}
