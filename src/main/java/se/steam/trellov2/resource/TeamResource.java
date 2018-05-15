package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.AbstractModel;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.resource.parameter.TaskInput;
import se.steam.trellov2.resource.mapper.Secured;
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.service.IssueService;
import se.steam.trellov2.service.TaskService;
import se.steam.trellov2.service.TeamService;
import se.steam.trellov2.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("teams")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public final class TeamResource {

    private final TeamService teamService;
    private final UserService userService;
    private final TaskService taskService;
    private final IssueService issueService;

    @Context
    private UriInfo uriInfo;

    public TeamResource(TeamService teamService, UserService userService, TaskService taskService, IssueService issueService) {
        this.teamService = teamService;
        this.userService = userService;
        this.taskService = taskService;
        this.issueService = issueService;
    }

    @POST
    @Secured
    public Response createTeam(Team team){
        return Response.created(getCreatedToDoUri(uriInfo, teamService.save(team))).build();
    }

    @GET
    @Path("{teamId}")
    public Response getTeam(@PathParam("teamId") UUID id) {
        return Response.ok(teamService.get(id)).build();
    }

    @PUT
    @Secured
    @Path("{teamId}")
    public void updateTeam(@PathParam("teamId") UUID teamId, Team team) {
        teamService.update(new Team(teamId, team.getName()));
    }

    @GET
    public Response getAllTeams() {
        return Response.ok(teamService.getAll()).build();
    }

    @GET
    @Path("{teamId}/users")
    public Response getAllUsersByTeam(@PathParam("teamId") UUID teamId) {
        return Response.ok(userService.getByTeam(teamId)).build();
    }

    @GET
    @Path("{teamId}/issues")
    public Response getAllTasksByPage(@PathParam("teamId") UUID teamId, @BeanParam PagingInput pagingInput){
        return Response.ok(issueService.getPage(teamId, pagingInput)).build();
    }

    @GET
    @Path("{teamId}/tasks")
    public List<Task> getByTeamAsPage(@PathParam("teamId") UUID teamId,
                                      @BeanParam PagingInput pagingInput,
                                      @BeanParam TaskInput taskInput) {
        return taskService.getByTeamAsPage(teamId, pagingInput, taskInput);
    }

    @POST
    @Secured
    @Path("{teamId}/tasks")
    public Response createTaskByTeam(@PathParam("teamId") UUID teamId, Task task) {
        return Response.created(getCreatedToDoUri(uriInfo, taskService.save(teamId, task))).build();
    }

    @PUT
    @Secured
    @Path("{teamId}/users/{userId}")
    public void addUserToTeam(@PathParam("teamId") UUID teamId,
                              @PathParam("userId") UUID userId) {
        teamService.addUserToTeam(teamId, userId);
    }

    @DELETE
    @Secured
    @Path("{teamId}/users/{userId}")
    public void leaveTeam(@PathParam("teamId") UUID teamId,
                          @PathParam("userId") UUID userId) {
        userService.leaveTeam(teamId, userId);
    }

    @DELETE
    @Secured
    @Path("{id}")
    public void removeTeam(@PathParam("id") UUID id) {
        teamService.remove(id);
    }

    private URI getCreatedToDoUri(UriInfo uriInfo, AbstractModel entity) {
        return uriInfo.getAbsolutePathBuilder().path(entity.getId().toString()).build();
    }

}