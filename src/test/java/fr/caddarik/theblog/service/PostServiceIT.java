/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import fr.caddarik.theblog.dao.jooq.converter.DateConverter;
import fr.caddarik.theblog.model.Post;
import fr.caddarik.theblog.model.User;
import fr.caddarik.theblog.service.exeption.ResourceNotFoundException;
import org.junit.Test;
import fr.caddarik.theblog.service.resource.PostService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

/**
 *
 * @author cedric
 */
@Slf4j
public class PostServiceIT extends ServiceIT {

    @Test
    public void testFind() throws ResourceNotFoundException {
        log.debug("testFind() empty keyword");
        PostService service = getService(PostService.class);
        List<Post> result = service.find("");
        Assert.assertTrue("Is there any post?", !result.isEmpty());

        log.debug("testFind() keyword");
        Post post = result.get(0);
        String keyword = post.getTitle().split(" ")[0];
        result = service.find(keyword);
        Assert.assertTrue("Is there any post?", !result.isEmpty());

        log.debug("testFind() keyword impossible");
        keyword = UUID.randomUUID().toString();
        result = service.find(keyword);
        Assert.assertTrue("There shouldn't be any result", result.isEmpty());

        log.debug("testFind() id");
        Post postResult = service.find(post.getId());
        Assert.assertEquals("the returned post is not the right one", post.getId(), postResult.getId());

        try {
            log.debug("testFind() wrong id");
            service.find(0);
            Assert.fail("no post should have id = " + 0);
        } catch (NotFoundException e) {
        }

    }

    @Test
    public void testCreateEditRemove() throws Exception {
        log.debug("testCreateEditRemove() create");
        PostService service = getService(PostService.class);
        Post post = getRandomPost(user);
        Integer id = service.create(post, user.getEmail(), user.getPassword());
        post.setId(id);
        assertPostDeepEquals(post, service.find(id));

        log.debug("testCreateEditRemove() edit");
        post.setTitle(post.getBody().split("-")[1]);
        post.setDate(new Date());
        service.edit(id, post, user.getEmail(), user.getPassword());
        assertPostDeepEquals(post, service.find(id));

        log.debug("testCreateEditRemove() remove");
        service.remove(id, user.getEmail(), user.getPassword());
        try {
            service.find(id);
            Assert.fail("Post " + id + " should have been removed");
        } catch (NotFoundException e) {
        }
    }

    @Test
    public void testIllegalAccess() throws Exception {
        log.debug("testIllegalAccess() create");
        PostService service = getService(PostService.class);
        User hacker = new User(3, "test", "test@domain.io", "123");
        Post post = getRandomPost(user);
        try {
            service.create(post, hacker.getEmail(), hacker.getPassword());
            Assert.fail("Post should not be created");
        } catch(NotAuthorizedException e) {}

        log.debug("testIllegalAccess() edit");
        Integer id = 1;
        Post target = service.find(id);
        try {
            service.edit(id, target, hacker.getEmail(), hacker.getPassword());
            Assert.fail("Post should not be edited");
        } catch(NotAuthorizedException e) {}
        
        log.debug("testIllegalAccess() remove");
        try {
            service.remove(id, hacker.getEmail(), hacker.getPassword());
            Assert.fail("Post should not be deleted");
        } catch(NotAuthorizedException e) {}
    }
    
    @Test
    public void testCreateInvalidPost() throws Exception {
        log.debug("testCreateInvalidPost()");
        PostService service = getService(PostService.class);
        Post post = getRandomPost(user);
        log.debug("testCreateInvalidPost() field null");
        testCreateInvalidPost(service, post, p -> p.setTitle(null), "title", user);
        testCreateInvalidPost(service, post, p -> p.setBody(null), "body", user);
        testCreateInvalidPost(service, post, p -> p.setDate(null), "date", user);
        testCreateInvalidPost(service, post, p -> p.setUser(null), "user", user);
        
        log.debug("testCreateInvalidPost() text field too big");
        testCreateInvalidPost(service, post, p -> p.setTitle(getText(200)), "title", user);
        testCreateInvalidPost(service, post, p -> p.setBody(getText(200)), "body", user);
    }

    private Post getRandomPost(User user) {
        Post post = new Post();
        String body = UUID.randomUUID().toString();
        post.setTitle("test " + body.split("-")[0]);
        post.setBody(body);
        post.setUser(user);
        post.setDate(new Date());
        return post;
    }

    private void assertPostDeepEquals(Post a, Post b) {
        Assert.assertEquals("id should be equals", a.getId(), b.getId());
        Assert.assertEquals("title should be equals", a.getTitle(), b.getTitle());
        Assert.assertEquals("body should be equals", a.getBody(), b.getBody());
        DateConverter converter = new DateConverter();
        Assert.assertTrue("date should be equals", converter.convertToDatabaseColumn(a.getDate()).equals(converter.convertToDatabaseColumn(b.getDate())));
        Assert.assertEquals("user.id should be equals", a.getUser().getId(), b.getUser().getId());
    }

    private void testCreateInvalidPost(PostService service, Post post, Consumer<Post> nullifier, String fieldName, User user) throws Exception {
        nullifier.accept(post);
        try {
            service.create(post, user.getEmail(), user.getPassword());
            Assert.fail("Post should not be created invalid " + fieldName);
        } catch (javax.ws.rs.BadRequestException | InternalServerErrorException e) {}
    }

    private String getText(int size) {
        return IntStream.range(0, size).mapToObj(i -> "a").collect(Collectors.joining());
    }
}
