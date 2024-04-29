package de.project.Project3.controllers;

import de.project.Project3.dto.MeasurementDTO;
import de.project.Project3.errorHandling.InvalidMeasurementDataException;
import de.project.Project3.errorHandling.SensorErrorResponse;
import de.project.Project3.models.Measurement;
import de.project.Project3.services.MeasurementService;
import de.project.Project3.util.MeasurementValidator;
import de.project.Project3.util.SensorValidator;
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
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final MeasurementService measurementService;
    private final SensorValidator sensorValidator;

    @Autowired
    public MeasurementController(ModelMapper modelMapper, MeasurementValidator measurementValidator, MeasurementService measurementService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.measurementService = measurementService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(convertDTOToMeasurement(measurementDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorBuilder = new StringBuilder();
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

            fieldErrorList.forEach(fieldError -> errorBuilder.append(fieldError.getDefaultMessage()).append(", "));

            throw new InvalidMeasurementDataException(errorBuilder.toString());
        }

        measurementService.registerMeasurement(convertDTOToMeasurement(measurementDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> fieldProblems(InvalidMeasurementDataException e) {
        return new ResponseEntity<>(new SensorErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @GetMapping
    public List<MeasurementDTO> getAll() {
        return measurementService.findAll().stream().map(this::convertMeasurementToDTO).toList();
    }

    @GetMapping("/rainyDays")
    public List<MeasurementDTO> getRainy() {
        return measurementService.findAllByRaining().stream().map(this::convertMeasurementToDTO).toList();
    }

    private Measurement convertDTOToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    private MeasurementDTO convertMeasurementToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
