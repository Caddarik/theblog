/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao.mapper;

import fr.caddarik.theblog.dao.jooq.tables.records.UserRecord;
import fr.caddarik.theblog.model.User;
import org.jooq.exception.MappingException;

/**
 * the mapper to bind a User with the jOOQ UserRecord
 * @author cedric
 */
public class UserMapper implements Mapper<User, UserRecord> {

    /**
     * 
     * @param record the jOOQ Record UserRecord
     * @return the corresponding User entity
     */
    @Override
    public User map(UserRecord record) {
        User e = new User(record.getId());
        e.setEmail(record.getEmail());
        e.setName(record.getName());
        e.setPassword(record.getPassword());
        return e;
    }

    /**
     * 
     * @param user the User entity to map
     * @return the corresponding UserRecord
     * @throws MappingException 
     */
    @Override
    public UserRecord unmap(User user) throws MappingException {
        UserRecord r = new UserRecord();
        r.setId(user.getId());
        r.setName(user.getName());
        r.setPassword(user.getPassword());
        r.setEmail(user.getEmail());
        return r;
    }
    
}
