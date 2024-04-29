package de.project.Project3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.project.Project3.models.Sensor;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class MeasurementDTO {
    @NotNull(message = "Value field cannot be empty")
    @DecimalMin(value = "-100.0", message = "Value is invalid (must be between -100 to 100)")
    @DecimalMax(value = "100.0", message = "Value is invalid (must be between -100 to 100)")
    private float value;

    @NotNull(message = "Raining field cannot be empty")
    private boolean raining;

    private LocalDateTime measuredAt;

    @NotNull(message = "The sensor data cannot be empty")
    private Sensor sensor;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(LocalDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
