/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author cedric
 */
public class Entity implements Serializable {

    protected Integer id;

    public Entity() {
    }

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            Entity that = (Entity) object;
            if (this.id == null && that.id == null) {
                return this == that;
            } else if (this.id == null || that.id == null) {
                return false;
            } else {
                return this.id.equals(that.id);
            }
        } else {
            return false;
        }
    }
    
    

}
