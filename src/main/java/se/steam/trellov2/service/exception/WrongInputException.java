package se.steam.trellov2.service.exception;

import javax.ws.rs.ext.Provider;

@Provider
public final class WrongInputException extends RuntimeException {

    public WrongInputException(String message) {
        super(message);
    }
}
