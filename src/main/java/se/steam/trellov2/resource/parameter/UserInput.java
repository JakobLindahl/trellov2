package se.steam.trellov2.resource.parameter;

import javax.ws.rs.QueryParam;

public class UserInput {

    @QueryParam("firstname")
    private String firstname;

    @QueryParam("lastname")
    private String lastname;

    @QueryParam("userame")
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
