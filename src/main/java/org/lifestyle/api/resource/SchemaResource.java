/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.lifestyle.api.View;
import org.lifestyle.api.model.FeedingSchema;
import org.lifestyle.api.model.User;
import org.lifestyle.api.persistence.SchemaDAO;
import org.lifestyle.api.service.SchemaService;

/**
 *
 * @author DannyW
 */
@Singleton
@Path("/schemas")
public class SchemaResource {
    
    private SchemaDAO dao;
    
    private final SchemaService service;
    
    @Inject
    public SchemaResource(SchemaService service){
        this.service = service;
        this.dao = new SchemaDAO();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed({"COACH"})
    public void getAll(@Auth User user)
    {
        service.getAll(user);
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("COACH")
    public FeedingSchema retrieve(@PathParam("id") int id, @Auth User user)
    {
        return service.getDetail(user, id);
    }
}
