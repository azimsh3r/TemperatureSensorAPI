package de.project.Project3.controllers;

import de.project.Project3.dto.SensorDTO;
import de.project.Project3.errorHandling.InvalidSensorDataException;
import de.project.Project3.errorHandling.SensorErrorResponse;
import de.project.Project3.models.Sensor;
import de.project.Project3.services.SensorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

            fieldErrorList.forEach(fieldError -> stringBuilder.append(fieldError.getDefaultMessage()).append(", "));
            throw new InvalidSensorDataException(stringBuilder.toString());
        }
        sensorService.register(convertDTOToSensor(sensorDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> invalid(InvalidSensorDataException e) {
        return new ResponseEntity<>(new SensorErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private Sensor convertDTOToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}