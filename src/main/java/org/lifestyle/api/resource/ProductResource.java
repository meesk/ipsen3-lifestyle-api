/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Collection;
import java.util.List;
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
import javax.json.*;
import javax.ws.rs.core.Response;
import org.lifestyle.api.View;
import org.lifestyle.api.model.Product;
import org.lifestyle.api.service.ProductService;

/**
 *
 * @author meesk
 */
@Singleton
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private final ProductService service;
    
    @Inject
    public ProductResource(ProductService service) {
        this.service = service;
    }
    
    @GET
    @JsonView(View.Private.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getAll(){
        return service.getAll();
    }

    @Path("/{id}")    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id")int id){
        service.delete(id);
    }
    
    @Path("/bulk")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToDAO(@Valid Product[] products){
        service.insertBulk(products);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addToDAO(@Valid Product product){
        service.insert(product);
    }
    
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id")int id,@Valid Product product){
        service.update(id,product);
    }
}
