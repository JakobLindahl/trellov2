package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.User;
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

    private final UserService userService;
    private final TaskService taskService;

    @Context
    private UriInfo uriInfo;

    private UserResource(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") UUID id){
        return userService.get(id);
    }

    @PUT
    @Path("{id}")
    public void updateUser(@PathParam("id") UUID id, User user){
        userService.update(new User(id,user.getUsername(),user.getFirstName(),user.getLastName(),user.isActive()));
    }

    @POST
    public Response postUser(User user){
        return Response.created(uriInfo.getAbsolutePathBuilder().path(userService.save(user).getId().toString()).build()).build();
    }

    @GET
    @Path("{id}/tasks")
    public List<Task> getTasksByUser(@PathParam("id") UUID userId){
        return taskService.getByUser(userId);
    }

    @PUT
    @Path("{userId}/tasks/{taskId}")
    public void addTaskToUser(@PathParam("userId") UUID userId, @PathParam("taskId") UUID taskId){
        userService.addTaskToUser(userId, taskId);
    }

}
