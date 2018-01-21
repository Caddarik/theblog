/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import fr.caddarik.theblog.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import fr.caddarik.theblog.service.resource.UserService;
import java.util.UUID;
import java.util.function.Consumer;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import org.junit.Assert;

/**
 *
 * @author cedric
 */
@Slf4j
public class UserServiceIT extends ServiceIT {

    @Test
    public void testFind() throws Exception {
        log.debug("testFind()");
        UserService service = getService(UserService.class);
        User result = service.find(user.getId(), user.getEmail(), user.getPassword());
        Assert.assertEquals("find return the wrong user", result.getId(), user.getId());
    }

    @Test
    public void testFindAbsent() throws Exception {
        log.debug("testFindAbsent()");
        UserService service = getService(UserService.class);
        try {
            service.find(0, user.getEmail(), user.getPassword());
            Assert.fail("find shouldn't return something");
        } catch (NotAuthorizedException rnfe) {
        }
    }

    @Test
    public void testCreateAndUpdate() throws Exception {
        log.debug("testCreate()");
        UserService service = getService(UserService.class);
        User u = getRandomUser();
        Integer id = service.create(u);
        log.debug("testCreate() {}", id);
        u.setId(id);
        User result = service.find(id, u.getEmail(), u.getPassword());
        assertUserDeepEquals(u, result);

        String secret = u.getPassword();
        u.setPassword("0000");
        service.edit(id, u, u.getEmail(), secret);
        result = service.find(id, u.getEmail(), u.getPassword());
        assertUserDeepEquals(u, result);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        log.debug("testCreateInvalid()");
        User rndUser = getRandomUser();
        UserService service = getService(UserService.class);
        testCreateInvalid(service, rndUser, u -> u.setName(null), "name");
        testCreateInvalid(service, rndUser, u -> u.setEmail(null), "email");
        testCreateInvalid(service, rndUser, u -> u.setPassword(null), "password");
    }

    @Test
    public void illegalAccessTest() throws Exception {
        log.debug("illegalAccessTest()");
        Integer id = 3;
        
        if(user.getId().equals(id)) {
            throw new IllegalStateException(id + " should not be equal to " + user.getId());
        }
        
        log.debug("illegalAccessTest() find");
        
        UserService service = getService(UserService.class);
        try {
            service.find(id, user.getEmail(), user.getPassword());
            Assert.fail("User " + user.getId() + " shouldn't be able to access User " + id);
        } catch (NotAuthorizedException e) {}
        
        log.debug("illegalAccessTest() edit");
        
        try {
            service.edit(id, user, user.getEmail(), user.getPassword());
            Assert.fail("User " + user.getId() + " shouldn't be able to access User " + id);
        } catch (BadRequestException e) {}
    }

    private void testCreateInvalid(UserService service, User u, Consumer<User> nullifier, String fieldName) throws Exception {
        nullifier.accept(u);
        try {
            service.create(u);
            Assert.fail("shouldn't create a user without " + fieldName);
        } catch (BadRequestException | InternalServerErrorException e) {}
    }

    private User getRandomUser() {
        String name = UUID.randomUUID().toString();
        return new User(null, name, name + "@mail.se", "123");
    }

    private void assertUserDeepEquals(User a, User b) {
        Assert.assertEquals("user.id should be equals", a.getId(), b.getId());
        Assert.assertEquals("user.name should be equals", a.getName(), b.getName());
        Assert.assertEquals("user.email should be equals", a.getEmail(), b.getEmail());
        Assert.assertEquals("user.password should be equals", a.getPassword(), b.getPassword());
    }
}
