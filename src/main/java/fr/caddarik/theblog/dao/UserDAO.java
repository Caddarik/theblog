/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao;

import static fr.caddarik.theblog.dao.jooq.Tables.USER;
import fr.caddarik.theblog.dao.mapper.UserMapper;
import fr.caddarik.theblog.dao.jooq.tables.UserTable;
import fr.caddarik.theblog.dao.jooq.tables.records.UserRecord;
import fr.caddarik.theblog.model.User;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Field;

/**
 *  The DAO for User
 * 
 * @author cedric
 */
@Stateless
@Slf4j
public class UserDAO extends AbstractDAO<User, UserRecord, UserTable> {

    private final UserMapper mapper = new UserMapper();

    @Override
    protected UserTable table() {
        return USER;
    }

    @Override
    protected Field<Integer> id() {
        return USER.ID;
    }

    @Override
    protected UserMapper mapper() {
        return mapper;
    }

    /**
     * 
     * @param login the find of the user wich is his email
     * @param secret the password crypted with PBKDF2WithHmacSHA1 algorithm
     * @return the corresponding user
     */
    public User find(@NotNull String login, @NotNull String secret) {
        return context().selectFrom(USER)
                    .where(USER.EMAIL.equalIgnoreCase(login))
                    .and(USER.PASSWORD.eq(secret))
                    .fetchOne(mapper);
    }

}
