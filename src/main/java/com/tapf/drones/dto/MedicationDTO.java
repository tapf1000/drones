package com.tapf.drones.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDTO {
    Long id;
    String name;
    Long weight;
    String code;
    byte[] image;
}
