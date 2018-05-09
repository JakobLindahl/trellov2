package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.service.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("tasks")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public final class TaskResource {

    private final TaskService service;

    @Context
    private UriInfo uriInfo;

    private TaskResource(TaskService taskService) {
        this.service = taskService;
    }

    @PUT
    @Path("{id}")
    public void updateTask(@PathParam("id") UUID id, Task task) {
        service.save(new Task(id, task.getText(), task.getStatus()));
    }

    @GET
    public List<Task> getTasksWithIssue() {
        return service.getWithIssue();
    }
}
