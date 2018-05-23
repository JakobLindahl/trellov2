package se.steam.trellov2.service.exception;

import javax.ws.rs.ext.Provider;

@Provider
public final class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
