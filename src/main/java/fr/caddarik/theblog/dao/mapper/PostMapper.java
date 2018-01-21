/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao.mapper;

import fr.caddarik.theblog.dao.converter.DateConverter;
import fr.caddarik.theblog.dao.jooq.tables.records.PostRecord;
import fr.caddarik.theblog.model.Post;
import fr.caddarik.theblog.model.User;
import org.jooq.exception.MappingException;

/**
 * the mapper to bind a Post with the jOOQ PostRecord
 * @author cedric
 */
public class PostMapper implements Mapper<Post, PostRecord>{
    
    private final DateConverter dateConverter = new DateConverter();
    
    /**
     * 
     * @param record the jOOQ PostRecord
     * @return the corresponding Post entity
     */
    @Override
    public Post map(PostRecord record) {
        Post entity = new Post(record.getId());
        entity.setUser(new User(record.getUserId()));
        entity.setDate(dateConverter.convertToEntityAttribute(record.getDate()));
        entity.setTitle(record.getTitle());
        entity.setBody(record.getBody());
        return entity;
    }

    /**
     * 
     * @param post the Post entity to map
     * @return the corresponding PostRecord
     * @throws MappingException 
     */
    @Override
    public PostRecord unmap(Post post) throws MappingException {
        PostRecord record = new PostRecord();
        record.setId(post.getId());
        record.setUserId(post.getUser().getId());
        record.setDate(dateConverter.convertToDatabaseColumn(post.getDate()));
        record.setTitle(post.getTitle());
        record.setBody(post.getBody());
        return record;
    }
    
}
