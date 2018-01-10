package org.lifestyle.api.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
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
import javax.ws.rs.core.MediaType;
import org.lifestyle.api.View;
import org.lifestyle.api.model.User;
import org.lifestyle.api.service.UserService;

/**
 * Meer informatie over resources:
 *  https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 * 
 * @author Peter van Vliet
 */
@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    private final UserService service;
    
    @Inject
    public UserResource(UserService service)
    {
        this.service = service;
    }
    
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed({"COACH", "ADMIN"})
    public Collection<User> retrieveAll()
    {
        return service.getAll();
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("COACH")
    public User retrieve(@PathParam("id") int id)
    {
        return service.getById(id);
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("COACH")
    public Collection<User> getTransferOptions(@PathParam("id") int id)
    {
        System.out.println("HEY IM IN THE TRANSFER OPTIONS! : " + id);
        return service.getTransferOptions(id);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(@Valid User user)
    {
        service.add(user);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed({"COACH", "ADMIN"})
    public void update(@PathParam("id") int id, @Auth User authenticator, User user)
    {
        service.update(authenticator, id, user);
    }
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id)
    {
        service.delete(id);
    }
    
    @POST
    @Path("/forgotpassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public boolean forgotPassword(User user) {
        return service.forgotPassword(user.getEmailAddress());
    }
    
    @GET
    @Path("/me")
    @JsonView(View.Public.class)
    public User authenticate(@Auth User authenticator)
    {
        return authenticator;
    }
}