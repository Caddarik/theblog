/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao;

import fr.caddarik.theblog.dao.jooq.mapper.Mapper;
import fr.caddarik.theblog.model.Entity;
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
import org.jooq.exception.NoDataFoundException;
import org.jooq.impl.DSL;

/**
 *
 * @author cedric
 * @param <E>
 * @param <R>
 * @param <T>
 */
@Slf4j
public abstract class AbstractDAO<E extends Entity, R extends UpdatableRecord, T extends Table<R>> implements DAO<E> {

    @Resource(lookup = "java:/jboss/datasources/BlogDS")
    private DataSource datasource;

    protected DSLContext context() {
        return DSL.using(datasource, SQLDialect.SQLITE);
    }

    @Override
    public Integer create(E entity) {
        R record = mapper().unmap(entity);
        
        InsertQuery<R> insert = context().insertQuery(table());
        insert.setRecord(record);
        insert.setReturning(id());
        int n = insert.execute();
        
        R created = insert.getReturnedRecord();
        Integer id = created.get(id(), Integer.class);
        log.debug("create({}) n={}, id={}", entity, n, id);
        return id;
    }

    @Override
    public void delete(Integer id) {
        int n = context()
                .delete(table())
                .where(id().equal(id))
                .execute();
        log.debug("delete({}) n={}", id, n);
    }

    @Override
    public void update(E entity) {
        R record = mapper().unmap(entity);
        Integer id = entity.getId();
        int n = context().update(table())
                .set(record)
                .where(id().eq(id))
                .execute();
        log.debug("update({}) n={}", entity, n);
    }

    @Override
    public E find(Integer id) throws NoDataFoundException {
        return context()
                .selectFrom(table())
                .where(id().equal(id))
                .fetchSingle(mapper());
    }

    @Override
    public List<E> findAll() {
        return context()
                .selectFrom(table())
                .fetchStream()
                .map(r -> mapper().map(r))
                .collect(toList()) ;
    }
    
    protected abstract T table();

    protected abstract Field<Integer> id();

    protected abstract Mapper<E,R> mapper();
}
