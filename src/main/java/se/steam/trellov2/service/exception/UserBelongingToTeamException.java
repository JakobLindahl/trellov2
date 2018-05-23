package se.steam.trellov2.service.exception;

import javax.ws.rs.ext.Provider;

@Provider
public final class UserBelongingToTeamException extends RuntimeException {

    public UserBelongingToTeamException(String s) {
        super(s);
    }
}
