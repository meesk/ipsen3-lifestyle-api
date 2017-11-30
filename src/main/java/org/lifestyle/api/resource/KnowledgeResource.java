/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @JsonView(View.Public.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Knowledge> getAll(){
        return service.getAll();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToDAO(@Valid Knowledge[] knowledge){
        service.insert(knowledge);
    }
}
