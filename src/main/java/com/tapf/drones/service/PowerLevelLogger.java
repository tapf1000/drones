package com.tapf.drones.service;

import com.tapf.drones.model.PowerLevelLogs;
import com.tapf.drones.repository.DroneRepository;
import com.tapf.drones.repository.PowerLevelLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PowerLevelLogger {
    @Value("${drone.power.logger.frequency}")
    static int frequency;
    static final int rate = 20;
    @Autowired PowerLevelLogsRepository powerLevelLogsRepository;
    @Autowired DroneRepository droneRepository;
    @Scheduled(fixedRate = rate*1000)
    private void log(){
        droneRepository.findAll().forEach(drone -> {
            powerLevelLogsRepository.save(new PowerLevelLogs(drone.getBattery(), drone.getSerialNumber()));
        });
    }
}
