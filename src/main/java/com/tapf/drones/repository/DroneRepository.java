package com.tapf.drones.repository;

import com.tapf.drones.model.Drone;
import org.springframework.data.repository.CrudRepository;


public interface DroneRepository extends CrudRepository<Drone, String> {
}
