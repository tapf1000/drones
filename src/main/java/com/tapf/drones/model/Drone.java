package com.tapf.drones.model;

import com.tapf.drones.utils.Model;
import com.tapf.drones.utils.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Drone {
    @Id
    String serialNumber;
    @Enumerated(EnumType.STRING)
    Model model;
    Long weightLimit;
    Integer battery;
    @Enumerated(EnumType.STRING)
    State state;
    @OneToMany()
    List<Medication> medication;

}
