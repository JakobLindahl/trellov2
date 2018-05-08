package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.User;
import se.steam.trellov2.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("users")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class UserResource {

    private final UserService service;

    @Context
    private UriInfo uriInfo;

    private UserResource(UserService service) {
        this.service = service;
    }

    @GET
    public List<User> getUsers(){
        return service.getAll();
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") UUID id){
        return service.get(id);
    }

    @PUT
    @Path("{id}")
    public void updateUser(@PathParam("id") UUID id, User user){
        service.save(new User(id,user.getUsername(),user.getFirstName(),user.getLastName(),user.isActive()));
    }

    @POST
    public Response postUser(User user){
        return Response.created(uriInfo.getAbsolutePathBuilder().path(service.save(user).getId().toString()).build()).build();
    }

    @GET
    @Path("{id}/tasks")
    public List<Task> getTasksByUser(@PathParam("id") UUID id){
        return null;
    }



}
