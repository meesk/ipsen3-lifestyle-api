/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lifestyle.api.model.User;

/**
 *
 * @author meesk
 */
@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloResource {
    
    @GET
    public String getGreeting(){
        return "Hello world!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String getGreeting(@Valid User user){
        return "Post hello";
    }
}
