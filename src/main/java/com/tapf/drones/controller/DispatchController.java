package com.tapf.drones.controller;

import com.tapf.drones.dto.DroneDTO;
import com.tapf.drones.dto.MedicationDTO;
import com.tapf.drones.model.Medication;
import com.tapf.drones.service.DispacthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DispatchController {
    @Autowired
    DispacthService dispacthService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public DroneDTO registerDrone(@RequestBody DroneDTO droneDTO){
        return dispacthService.mapDroneFromEntity(dispacthService.registerDrone(dispacthService.mapDroneToEntity(droneDTO)));
    }
    @PostMapping(value = "/load/{serialNo}", consumes = "multipart/form-data", produces = "application/json")
    public MedicationDTO loadDrone(@PathVariable String serialNo, @RequestPart MultipartFile image, String name, String code, Long weight) throws IOException {
        MedicationDTO medication = MedicationDTO.builder().image(image.getBytes()).name(name).code(code).weight(weight).build();
        return dispacthService.loadDrone(serialNo, medication);
    }

    @GetMapping(value = "/medications/{serialNo}", produces = "application/json")
    public List<MedicationDTO> checkMedications(@PathVariable String serialNo){
        return dispacthService.retrieveMedications(serialNo);
    }

    @GetMapping(value = "/availability", produces = "application/json")
    public List<DroneDTO> getAvailableDrones(){
        return dispacthService.getAvailableDrones();
    }

    @GetMapping(value = "/battery/{serialNo}", produces = "application/json")
    public Integer getBatteryLevel(@PathVariable String serialNo){
        return dispacthService.getBatteryLevel(serialNo);
    }

}
