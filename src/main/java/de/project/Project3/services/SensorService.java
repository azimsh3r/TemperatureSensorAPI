package de.project.Project3.services;

import de.project.Project3.dto.SensorDTO;
import de.project.Project3.models.Sensor;
import de.project.Project3.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public List<Sensor> findByName(String name) {
        return sensorRepository.findAllByName(name);
    }

    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
