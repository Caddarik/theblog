/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cedric
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Post extends Entity {

    @NotNull
    @Size(min = 1, max = 150)
    private String title;
    
    @NotNull
    @Size(min = 1, max = 150)
    private String body;
    
    @NotNull
    private Date date;
    
    @NotNull
    private User user;

    public Post() {
    }

    public Post(Integer id) {
        super(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post[ id=" + id
                + ", user_id=" + user
                + ", date=" + date
                + ", title=" + title
                + ", body=" + body + " ]";
    }
    
}