package se.steam.trellov2.service.exception;

import javax.ws.rs.ext.Provider;

@Provider
public final class InactiveEntityException extends RuntimeException {

    public InactiveEntityException(String message) {
        super(message);
    }
}