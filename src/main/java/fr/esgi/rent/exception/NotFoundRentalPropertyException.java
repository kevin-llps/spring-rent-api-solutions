package fr.esgi.rent.exception;

public class NotFoundRentalPropertyException extends RuntimeException {

    public NotFoundRentalPropertyException(String message) {
        super(message);
    }
}
