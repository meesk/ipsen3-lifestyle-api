/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
@Produces(MediaType.APPLICATION_JSON)
public class SchemaResource {
    
    private SchemaDAO dao;
    
    private final SchemaService service;
    
    @Inject
    public SchemaResource(SchemaService service) {
        this.service = service;
        this.dao = new SchemaDAO();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed({"COACH"})
    public Collection<FeedingSchema> getAll(@Auth User user) {
        return service.getAll(user);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed({"COACH"})
    public String insert(FeedingSchema schema) {
        int id = service.insert(schema);
       String value = Json.createObjectBuilder()
        .add("id", id).build().toString();
       
       return value;
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed({"COACH"})
    public void delete(@PathParam("id") int id){
        service.delete(id);
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
