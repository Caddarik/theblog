/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import fr.caddarik.theblog.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import fr.caddarik.theblog.service.resource.SecurityService;
import javax.ws.rs.NotFoundException;
import org.junit.Assert;

/**
 *
 * @author cedric
 */
@Slf4j
public class SecurityServiceIT extends ServiceIT {

    /**
     * Test of login method, of class SecurityService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoginSuccess() throws Exception {
        log.debug("testLoginSuccess");
        SecurityService service = getService(SecurityService.class);
        User result = service.login(user.getEmail(), user.getPassword());
        Assert.assertEquals("The returned user is not the right one when using login()", user.getId(), result.getId());
    }

    @Test
    public void testLoginWrongLogin() throws Exception {
        log.debug("testLoginWrongLogin");
        SecurityService service = getService(SecurityService.class);
        try {
            service.login("Wrong login", user.getPassword());
            Assert.fail("testLoginWrongLogin should fail");
        } catch (NotFoundException e) {
            log.debug("testLoginWrongLogin() raise", e);
        }
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        log.debug("testLoginWrongPassword");
        SecurityService service = getService(SecurityService.class);
        try {
            service.login(user.getEmail(), "wrong password");
            Assert.fail("testLoginWrongPassword should fail");
        } catch (NotFoundException e) {
            log.debug("testLoginWrongPassword() raise", e);
        }
    }

}
