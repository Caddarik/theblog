/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.security;

import fr.caddarik.theblog.dao.PostDAO;
import fr.caddarik.theblog.dao.UserDAO;
import fr.caddarik.theblog.model.Post;
import fr.caddarik.theblog.model.User;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cedric
 */
@Stateless
@Slf4j
public class AuthorizationChecker {

    @Inject
    private UserDAO userDAO;

    @Inject
    private PostDAO postDAO;

    /**
     *
     * @param login the email of the user
     * @param secret the password crypted with PBKDF2WithHmacSHA1 algorithm
     * @return the corresponding user
     * @throws LoginException if login or password is incorrect
     */
    public User login(@NotNull String login, @NotNull String secret) throws LoginException {
        log.debug("login({},{})", login, secret);
        User user = userDAO.login(login, secret);
        if (user == null) {
            log.debug("login({},{}) faild", login, secret);
            throw new LoginException("Incorrect login or passoword");
        }
        log.debug("login({},{}) success", login, secret);
        return user;
    }

    /**
     *
     * @param login the email of the user
     * @param secret the password crypted with PBKDF2WithHmacSHA1 algorithm
     * @param userId the id of the user targeted
     * @throws AuthException
     * @throws LoginException
     */
    public void checkForUser(@NotNull String login, @NotNull String secret, Integer userId) throws AuthException, LoginException {
        log.debug("checkForUser({},{},{})", login, secret, userId);
        User actual = login(login, secret);
        if (!Objects.equals(actual.getId(), userId)) {
            log.debug("checkForUser({},{},{}) not allowed", login, secret, userId);
            throw new AuthException("User " + actual.getId() + " is not allowed to access User " + userId);
        }
        log.debug("checkForUser({},{},{}) allowed", login, secret, userId);
    }

    /**
     *
     * @param login the email of the user
     * @param secret the password crypted with PBKDF2WithHmacSHA1 algorithm
     * @param postId the id of the post targeted
     * @throws AuthException
     * @throws LoginException
     */
    public void checkForPost(String login, String secret, Integer postId) throws AuthException, LoginException {
        log.debug("checkForPost({},{},{})", login, secret, postId);
        User user = login(login, secret);
        Post post = postDAO.find(postId);
        if(user.getId().equals(post.getUser().getId())) {
            log.debug("checkForPost({},{},{}) Allowed", login, secret, postId);
        } else {
            log.debug("checkForPost({},{},{}) Not allowed", login, secret, postId);
            throw new AuthException("User " + user.getId() + " is not allowed to access Post " + postId);
        }
    }
}
