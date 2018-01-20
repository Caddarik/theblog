/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao.jooq.mapper;

import fr.caddarik.theblog.model.Entity;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordUnmapper;

/**
 *  the mapper to bind an entity with the jOOQ Record
 * @author cedric
 * @param <E>
 * @param <R>
 */
public interface Mapper<E extends Entity, R extends Record> extends RecordMapper<R, E>, RecordUnmapper<E, R> {

}
