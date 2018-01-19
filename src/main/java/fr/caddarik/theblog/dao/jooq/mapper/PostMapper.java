/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao.jooq.mapper;

import fr.caddarik.theblog.dao.jooq.converter.DateConverter;
import fr.caddarik.theblog.dao.jooq.tables.records.PostRecord;
import fr.caddarik.theblog.model.Post;
import fr.caddarik.theblog.model.User;
import org.jooq.exception.MappingException;

/**
 *
 * @author cedric
 */
public class PostMapper implements Mapper<Post, PostRecord>{
    
    private final DateConverter dateConverter = new DateConverter();
    
    @Override
    public Post map(PostRecord record) {
        Post entity = new Post(record.getId());
        entity.setUser(new User(record.getUserId()));
        entity.setDate(dateConverter.convertToEntityAttribute(record.getDate()));
        entity.setTitle(record.getTitle());
        entity.setBody(record.getBody());
        return entity;
    }

    @Override
    public PostRecord unmap(Post entity) throws MappingException {
        PostRecord record = new PostRecord();
        record.setId(entity.getId());
        record.setUserId(entity.getUser().getId());
        record.setDate(dateConverter.convertToDatabaseColumn(entity.getDate()));
        record.setTitle(entity.getTitle());
        record.setBody(entity.getBody());
        return record;
    }
    
}
