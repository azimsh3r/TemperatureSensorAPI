package de.project.Project3.util;

import de.project.Project3.models.Measurement;
import de.project.Project3.services.MeasurementService;
import de.project.Project3.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Measurement.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (measurement.getSensor().getName().length() < 3 || measurement.getSensor().getName().length() > 30) {
            errors.rejectValue("sensor", "", "The sensor name is invalid (length between 3 and 30)");
        }

        if(sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor with the name is is not found");
        }
    }
}
