package se.steam.trellov2.service.exceptions;

public final class WrongInputException extends RuntimeException {

    public WrongInputException(String message) {
        super(message);
    }
}
