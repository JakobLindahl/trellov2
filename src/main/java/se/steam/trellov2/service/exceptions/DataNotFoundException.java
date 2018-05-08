package se.steam.trellov2.service.exceptions;

public final class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
