/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao;

import fr.caddarik.theblog.dao.mapper.Mapper;
import fr.caddarik.theblog.model.Entity;
import fr.caddarik.theblog.service.exeption.ResourceNotFoundException;
import fr.caddarik.theblog.service.exeption.ResourcePersistanceException;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertQuery;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DSL;

/**
 *
 * @author cedric
 * @param <E> The class of the entity
 * @param <R> The class of the jooq record
 * @param <T> The class of the jooq table
 */
@Slf4j
public abstract class AbstractDAO<E extends Entity, R extends UpdatableRecord, T extends Table<R>> implements DAO<E> {

    @Resource(lookup = "java:/jboss/datasources/BlogDS")
    private DataSource datasource;

    protected DSLContext context() {
        return DSL.using(datasource, SQLDialect.SQLITE);
    }

    @Override
    public Integer create(E entity) throws ResourcePersistanceException {
        try {

            R record = mapper().unmap(entity);
            InsertQuery<R> insert = context().insertQuery(table());
            insert.setRecord(record);
            insert.setReturning(id());
            int n = insert.execute();

            R created = insert.getReturnedRecord();
            Integer id = created.get(id(), Integer.class);
            log.debug("create({}) n={}, id={}", entity, n, id);
            return id;
        } catch (DataAccessException dae) {
            throw new ResourcePersistanceException("an error occured in the database, the resource was not created", dae);
        }
    }

    @Override
    public void delete(Integer id) throws ResourcePersistanceException, ResourceNotFoundException {
        try {
            int n = context()
                    .delete(table())
                    .where(id().equal(id))
                    .execute();
            log.debug("delete({}) n={}", id, n);
            if (n == 0) {
                throw new ResourceNotFoundException("Resource " + id + " not found");
            }
        } catch (DataAccessException dae) {
            throw new ResourcePersistanceException("an error occured in the database the resource " + id + " was not deleted", dae);
        }
    }

    @Override
    public void update(E entity) throws ResourcePersistanceException, ResourceNotFoundException {
        try {
            R record = mapper().unmap(entity);
            Integer id = entity.getId();
            int n = context().update(table())
                    .set(record)
                    .where(id().eq(id))
                    .execute();
            log.debug("update({}) n={}", entity, n);
            if (n == 0) {
                throw new ResourceNotFoundException("Resource " + id + " not found");
            }
        } catch (DataAccessException dae) {
            throw new ResourcePersistanceException("an error occured in the database the resource " + entity.getId() + " was not updated", dae);
        }
    }

    @Override
    public E find(Integer id) throws ResourceNotFoundException {
        try {
            return context()
                    .selectFrom(table())
                    .where(id().equal(id))
                    .fetchSingle(mapper());
        } catch (NoDataFoundException ndfe) {
            throw new ResourceNotFoundException("Resource " + id + " not found", ndfe);
        }
    }

    @Override
    public List<E> findAll() {
        return context()
                .selectFrom(table())
                .fetchStream()
                .map(r -> mapper().map(r))
                .collect(toList());
    }

    /**
     *
     * @return The jOOQ modelistaion of the table
     */
    protected abstract T table();

    /**
     * @return The jOOQ modelistaion of the primary key
     */
    protected abstract Field<Integer> id();

    /**
     *
     * @return the mapper to bind an entity with the jOOQ Record
     */
    protected abstract Mapper<E, R> mapper();
}
