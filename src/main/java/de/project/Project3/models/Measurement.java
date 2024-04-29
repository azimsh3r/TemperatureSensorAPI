package de.project.Project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Value field cannot be empty")
    @Column(name="value")
    private float value;

    @NotNull(message = "Raining field cannot be empty")
    @Column(name="raining")
    private boolean raining;

    @NotNull(message = "Sensor data cannot be empty")
    @Column(name="sensor_id")
    private int sensorId;

    @Column(name="measured_at")
    private LocalDateTime measuredAt;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Sensor sensor;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
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
};
