package de.project.Project3.errorHandling;

public class InvalidMeasurementDataException extends RuntimeException {
    public InvalidMeasurementDataException(String message) {
        super(message);
    }
}
