package com.tapf.drones.dto;

import com.tapf.drones.utils.Model;
import com.tapf.drones.utils.State;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DroneDTO {
    String serialNumber;
    Model model;
    Long weight;
    Integer percentage;
    State state;
}
