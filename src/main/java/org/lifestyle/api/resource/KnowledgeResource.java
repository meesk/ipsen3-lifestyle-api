/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.lifestyle.api.View;
import org.lifestyle.api.model.Knowledge;
import org.lifestyle.api.service.KnowledgeService;

/**
 *
 * @author Laurens Jan
 */
@Singleton
@Path("/knowledge")
public class KnowledgeResource {
    
    private final KnowledgeService service;
    @Inject
    public KnowledgeResource(KnowledgeService service){
        this.service=service;
    }
    
    @GET
    @JsonView(View.Private.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Knowledge> getAll(){
        return service.getAll();
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@QueryParam("id")List<Integer> id){
        service.delete(id);
    }
    
    @Path("/bulk")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToDAO(@Valid Knowledge[] knowledge){
        service.insertBulk(knowledge);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToDAO(@Valid Knowledge knowledge){
        service.insert(knowledge);
    }
    
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id")int id,@Valid Knowledge knowledge){
        service.update(id,knowledge);
    }
}
