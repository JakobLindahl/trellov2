package se.steam.trellov2.resource.parameter;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class UserInput {

    @QueryParam("firstName")
    @DefaultValue("")
    private String firstname;

    @QueryParam("lastName")
    @DefaultValue("")
    private String lastname;

    @QueryParam("username")
    @DefaultValue("")
    private String username;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }
}
