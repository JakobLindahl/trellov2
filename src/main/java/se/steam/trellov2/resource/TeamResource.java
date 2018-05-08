package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.service.TeamService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("teams")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public final class TeamResource {

    private final TeamService teamService;
    @Context
    private UriInfo uriInfo;

    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    @POST
    public Response createTeam(Team team){
        return Response.created(getCreatedToDoUri(uriInfo, teamService.save(team))).build();
    }

    @GET
    @Path("{teamId}")
    public Response getTeam(@PathParam("teamId") UUID id) {
        return Response.ok(teamService.get(id)).build();
    }

    @PUT
    @Path("{teamId}")
    public void updateTeam(Team team){
        teamService.update(team);
    }

    @GET
    public Response getAllTeams(){
        return Response.ok(teamService.getAll()).build();
    }

    @PUT
    @Path("{teamId}/users/{userId}")
    public void addUserToTeam(@PathParam("teamId") UUID teamId,
                              @PathParam("userId") UUID userId){
        teamService.addUserToTeam(teamId, userId);
    }

    private URI getCreatedToDoUri(UriInfo uriInfo, Team team) {
        return uriInfo.getAbsolutePathBuilder().path(team.getId().toString()).build();
    }

}
