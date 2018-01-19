/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.service;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * Oracle Jax-RS tuto : https://docs.oracle.com/javaee/7/tutorial/jaxrs.htm
 * JBoss Jax-RS tuto : https://docs.jboss.org/author/display/WFLY9/Java+API+for+RESTful+Web+Services+%28JAX-RS%29
 * JBoss RESTEasy doc : http://docs.jboss.org/resteasy/docs/3.1.4.Final/userguide/html_single/index.html
 * Netbeans : https://netbeans.org/kb/docs/websvc/rest.html#create-project
 * Oracle : http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/RESTfulWebServices/RESTfulWebservices.htm
 * 
 * @author cedric
 */
@ApplicationPath("service")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(fr.caddarik.theblog.service.PostService.class);
        resources.add(fr.caddarik.theblog.service.SecurityService.class);
        resources.add(fr.caddarik.theblog.service.UserService.class);
        resources.add(fr.caddarik.theblog.service.exeption.AuthExceptionMapper.class);
        resources.add(fr.caddarik.theblog.service.exeption.IllegalArgumentExceptionMapper.class);
        resources.add(fr.caddarik.theblog.service.exeption.LoginExceptionMapper.class);
        resources.add(fr.caddarik.theblog.service.exeption.ThrowableExceptionMapper.class);
    }
    
}
