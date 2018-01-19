/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao.jooq.mapper;

import fr.caddarik.theblog.dao.jooq.tables.records.UserRecord;
import fr.caddarik.theblog.model.User;
import org.jooq.exception.MappingException;

/**
 *
 * @author cedric
 */
public class UserMapper implements Mapper<User, UserRecord> {

    @Override
    public User map(UserRecord r) {
        User e = new User(r.getId());
        e.setEmail(r.getEmail());
        e.setName(r.getName());
        e.setPassword(r.getPassword());
        return e;
    }

    @Override
    public UserRecord unmap(User e) throws MappingException {
        UserRecord r = new UserRecord();
        r.setId(e.getId());
        r.setName(e.getName());
        r.setPassword(e.getPassword());
        r.setEmail(e.getEmail());
        return r;
    }
    
}
