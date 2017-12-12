
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
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
import org.lifestyle.api.model.Client;
import org.lifestyle.api.persistence.ClientDAO;
import org.lifestyle.api.service.ClientService;



/**
 *
 * @author abdoul
 */
@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {
    private ClientDAO dao;
    
    private final ClientService service;
    
    @Inject
    public ClientResource(ClientService service){
        this.service = service;
        this.dao = new ClientDAO();
    }
    
    @GET
    @JsonView(View.Public.class)
     public Collection<Client> getAll(){
        return service.getAllClients();
    }
    
    @Path("/client")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void createClient(Client client){
        service.addClient(client);
        System.out.println(client.getName());
        System.out.println(client.getHeight());
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void deleteClient(@QueryParam("id")int id){
        service.deleteClient(id);
    }
   
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void updateClient(@PathParam("id")int id, Client client){
       service.updateClient(id, client);
    }
    
}
