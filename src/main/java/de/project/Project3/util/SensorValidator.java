package de.project.Project3.util;

import de.project.Project3.dto.SensorDTO;
import de.project.Project3.models.Sensor;
import de.project.Project3.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        System.out.println("At least here");
        if(sensorService.findByName(sensor.getName()).isEmpty()) {
            System.out.println("I am here ");
            errors.rejectValue("name", "", "Sensor with the name is is not found");
        }
    }
}
