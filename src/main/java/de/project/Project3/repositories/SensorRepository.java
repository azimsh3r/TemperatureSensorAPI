package de.project.Project3.repositories;

import de.project.Project3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findAllByName(String name);
}
