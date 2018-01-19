/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao;

import fr.caddarik.theblog.model.Entity;
import java.util.List;
import org.jooq.exception.NoDataFoundException;

/**
 *
 * @author cedric
 * @param <E>
 */
public interface DAO<E extends Entity>  {
    
    Integer create(E entity);
    
    E find(Integer id) throws NoDataFoundException;
    
    List<E> findAll();
    
    void update(E entity);
    
    void delete(Integer id);
    
    public default void delete(E entity) {
        delete(entity.getId());
    }
}
