package de.project.Project3.dto;

import de.project.Project3.models.Measurement;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class SensorDTO {

    @Size(min = 3, max = 30, message = "Length of name of sensor must be between 3 and 30")
    @NotNull(message = "Name of the sensor cannot be null")
    private String name;

    private List<Measurement> measurementList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }
}
