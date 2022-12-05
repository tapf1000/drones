package com.tapf.drones.service;

import com.tapf.drones.dto.DroneDTO;
import com.tapf.drones.dto.MedicationDTO;
import com.tapf.drones.model.Drone;
import com.tapf.drones.model.Medication;
import com.tapf.drones.repository.DroneRepository;
import com.tapf.drones.repository.MedicationRepository;
import com.tapf.drones.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DispacthService {

    @Autowired DroneRepository droneRepository;
    @Autowired MedicationRepository medicationRepository;

    public Drone registerDrone(Drone drone){
        return droneRepository.save(drone);
    }

    public Drone mapDroneToEntity(DroneDTO droneDTO){
        return Drone.builder()
                .serialNumber(droneDTO.getSerialNumber())
                .model(droneDTO.getModel())
                .battery(droneDTO.getPercentage())
                .state(droneDTO.getState())
                .weightLimit(droneDTO.getWeight())
                .build();
    }

    public DroneDTO mapDroneFromEntity(Drone drone){
        return DroneDTO.builder()
                .serialNumber(drone.getSerialNumber())
                .weight(drone.getWeightLimit())
                .state(drone.getState())
                .percentage(drone.getBattery())
                .model(drone.getModel())
                .build();
    }

    public MedicationDTO loadDrone(String serialNumber, MedicationDTO m){
        if(isBelowOrEqualDroneWeightLimit(serialNumber,m) && isBatteryLevelAllowLoading(serialNumber)) {
        Medication meds = Medication.builder()
                .id(m.getId())
                .weight(m.getWeight())
                .image(m.getImage())
                .name(m.getName())
                .code(m.getCode())
                .build();
        List<Medication> medication = new ArrayList<>();
            medication.add(medicationRepository.save(meds));
        Drone drone = droneRepository.findById(serialNumber).get();
        drone.setMedication(medication);
        droneRepository.save(drone);
        m.setId(medication.get(0).getId());
        return m;
        } else return null; //TODO
    }

    private boolean isBatteryLevelAllowLoading(String serialNumber) {
        return droneRepository.findById(serialNumber).get().getBattery() < 25 ? false : true;
    }

    private boolean isBelowOrEqualDroneWeightLimit(String serialNumber, MedicationDTO m){
        final boolean[] state = new boolean[1];
        droneRepository.findById(serialNumber).ifPresent(d -> { state[0] = d.getWeightLimit() >= m.getWeight();});
        return state[0];
    }

    private List<MedicationDTO> mapEntityToMedicationDTO(List<Medication> medications){
        return medications.stream().map(m -> MedicationDTO.builder()
                .id(m.getId())
                .weight(m.getWeight())
                .name(m.getName())
                .image(m.getImage())
                .code(m.getCode())
                .build()).collect(Collectors.toList());
    }

    public List<MedicationDTO> retrieveMedications(String serialNo) {
        Optional<Drone> droneOpt = droneRepository.findById(serialNo);
        if(droneOpt.isPresent()){
            return mapEntityToMedicationDTO(droneOpt.get().getMedication());
        }
        //TODO : Return 404 Status Code Instead Of Empty Array
        else return Arrays.asList();
    }

    public List<DroneDTO> getAvailableDrones() {
        List<Drone> droneList = (List<Drone>) droneRepository.findAll();
        return droneList.stream().filter(d -> d.getState().equals(State.IDLE)).map(drone -> mapDroneFromEntity(drone))
        .collect(Collectors.toList());
    }

    public Integer getBatteryLevel(String serialNo){
         return droneRepository.findById(serialNo).map(Drone::getBattery).get();
    }
}
