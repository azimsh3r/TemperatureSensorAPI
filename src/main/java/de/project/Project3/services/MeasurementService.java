package de.project.Project3.services;

import de.project.Project3.dto.MeasurementDTO;
import de.project.Project3.models.Measurement;
import de.project.Project3.models.Sensor;
import de.project.Project3.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = false)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public void registerMeasurement(Measurement measurement) {
        Sensor sensor = sensorService.findByName(measurement.getSensor().getName()).get(0);
        measurement.setMeasuredAt(LocalDateTime.now());
        measurement.setSensor(sensor);
        measurement.setSensorId(sensor.getId());

        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public List<Measurement> findAllByRaining() {
        return measurementRepository.findByRaining(true);
    }
}
