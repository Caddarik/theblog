/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao;

import fr.caddarik.theblog.model.Entity;
import fr.caddarik.theblog.service.exeption.ResourceNotFoundException;
import fr.caddarik.theblog.service.exeption.ResourcePersistanceException;
import java.util.List;

/**
 *
 * The Data Access Object (DAO) to perform CRUD operations on Entity
 * 
 * @author cedric
 * @param <E>
 */
public interface DAO<E extends Entity>  {
    
    /**
     * A method to create a new entity
     * 
     * @param entity the entiry to create
     * @return the id of the created entity
     * @throws ResourcePersistanceException if the entity is not persisted
     */
    Integer create(E entity) throws ResourcePersistanceException;
    
    /**
     * 
     * @param id the id of the entity to find
     * 
     * @return the corresponding entity
     * @throws ResourceNotFoundException if the entity is not found
     */
    E find(Integer id) throws ResourceNotFoundException;
    
    /**
     * a method to find all entities
     * @return a list containing all entities
     */
    List<E> findAll();
    
    /**
     * a method to update an entity
     * @param entity the entity to update
     * @throws ResourceNotFoundException if no entity with the corresponding entity has been found
     * @throws ResourcePersistanceException if the entity was not updated
     */
    void update(E entity) throws ResourceNotFoundException, ResourcePersistanceException;
    
    /**
     * A method to delete an entity by the id
     * @param id
     * @throws ResourcePersistanceException if the entity was not delete
     * @throws ResourceNotFoundException if no entity with the corresponding entity has been found
     */
    void delete(Integer id) throws ResourcePersistanceException, ResourceNotFoundException;
    
    /**
     * A method to delete an entity
     * @param entity the entity to delete
     * @throws ResourcePersistanceException if the entity was not delete
     * @throws ResourceNotFoundException if no entity with the corresponding entity has been found 
     */
    public default void delete(E entity) throws ResourcePersistanceException, ResourceNotFoundException {
        delete(entity.getId());
    }
}
