package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.User;
import se.steam.trellov2.resource.parameter.UserInput;
import se.steam.trellov2.service.TaskService;
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

    private final UserService uService;
    private final TaskService tService;

    @Context
    private UriInfo uriInfo;

    private UserResource(UserService uService, TaskService tService) {
        this.uService = uService;
        this.tService = tService;
    }

    @GET
    public List<User> getUsers(@BeanParam UserInput input) {
        return uService.getAll(input);
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") UUID id) {
        return uService.get(id);
    }

    @PUT
    @Path("{id}")
    public void updateUser(@PathParam("id") UUID id, User user) {
        uService.update(new User(id, user.getUsername(), user.getFirstName(), user.getLastName(), user.isActive()));
    }

    @POST
    public Response postUser(User user) {
        return Response.created(uriInfo.getAbsolutePathBuilder().path(uService.save(user).getId().toString()).build()).build();
    }

    @DELETE
    @Path("{id}")
    public void removeUser(@PathParam("id") UUID id){
        uService.remove(id);
    }

    @GET
    @Path("{id}/tasks")
    public List<Task> getTasksByUser(@PathParam("id") UUID id) {
        return tService.getByUser(id);
    }

    @PUT
    @Path("{id}/tasks/{taskId}")
    public void addTaskToUser(@PathParam("id") UUID id, @PathParam("taskId") UUID taskId) {
        uService.addTask(id, taskId);
    }
}
