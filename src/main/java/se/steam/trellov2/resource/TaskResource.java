package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.service.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("tasks")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService;

    @Context
    private UriInfo uriInfo;

    private TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @PUT
    @Path("{id}")
    public void updateTask(@PathParam("id") UUID id, Task task) {
        taskService.save(new Task(id,task.getText(),task.getStatus()));
    }
}
