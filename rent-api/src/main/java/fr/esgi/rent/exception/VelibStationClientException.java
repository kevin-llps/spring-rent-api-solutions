package fr.esgi.rent.exception;

public class VelibStationClientException extends RuntimeException {
    public VelibStationClientException(String message) {
        super(message);
    }

    public VelibStationClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
