/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao;

import static fr.caddarik.theblog.dao.jooq.Tables.POST;
import fr.caddarik.theblog.dao.jooq.mapper.PostMapper;
import fr.caddarik.theblog.dao.jooq.tables.PostTable;
import fr.caddarik.theblog.dao.jooq.tables.records.PostRecord;
import fr.caddarik.theblog.model.Post;
import java.util.List;
import javax.ejb.Stateless;
import org.jooq.Condition;
import org.jooq.Field;

/**
 * The DAO for Post
 * 
 * @author cedric
 */
@Stateless
public class PostDAO extends AbstractDAO<Post, PostRecord, PostTable> {

    private final PostMapper mapper = new PostMapper();
    
    /**
     * Return all the posts with the specified keyword in the title or body
     * 
     * this method work with an SQL queries
     * It could be uppgrated by using a search engine like ElasticSearch instead
     * 
     * @param keyword the keyword to search
     * @return the matching posts
     */
    public List<Post> search(String keyword) {
        if(keyword==null || keyword.isEmpty()) {
            return findAll();
        }
        
        return context().selectFrom(POST)
                .where(getSearchCondition(keyword))
                .fetch(mapper);
    }

    /**
     * 
     * @param keyword
     * @return the Condition in the TITLE or in the BODY
     */
    private Condition getSearchCondition(String keyword) {
        return getSearchCondition(keyword, POST.TITLE)
                .or(getSearchCondition(keyword, POST.BODY));
    }

    /**
     * 
     * @param keyword the keyword to search
     * @param field the field to match the keyword with
     * @return the Condition field like %keyword%
     */
    private Condition getSearchCondition(String keyword, Field<String> field) {
        return field.like("%"+keyword+"%");
    }
    
    @Override
    protected PostTable table() {
        return POST;
    }

    @Override
    protected Field<Integer> id() {
        return POST.ID;
    }

    @Override
    protected PostMapper mapper() {
        return mapper;
    }
    
}
