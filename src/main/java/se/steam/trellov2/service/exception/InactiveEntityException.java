package se.steam.trellov2.service.exception;

public class InactiveEntityException extends RuntimeException {

    public InactiveEntityException(String message) {
        super(message);
    }
}