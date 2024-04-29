package de.project.Project3.errorHandling;

public class InvalidSensorDataException extends RuntimeException{

    public InvalidSensorDataException(String message) {
        super(message);
    }
}
