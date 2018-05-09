package se.steam.trellov2.resource;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.service.IssueService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("issues")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class IssueResource {

    private final IssueService issueService;

    @Context
    private UriInfo uriInfo;

    public IssueResource(IssueService issueService) {
        this.issueService = issueService;
    }

    @PUT
    @Path("{id}")
    public void updateIssue(@PathParam("id") UUID id, Issue issue){
        issueService.update(new Issue(id,issue.getDescription()));
    }
}
