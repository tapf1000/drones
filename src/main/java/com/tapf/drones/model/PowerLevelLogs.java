package com.tapf.drones.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PowerLevelLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long logId;
    Integer batteryLevel;
    String droneSerialNo;
    LocalDateTime logTime;

    @PrePersist
    private void logTime(){
        logTime = LocalDateTime.now();
    }

    public PowerLevelLogs(Integer batteryLevel, String droneSerialNo){
        this.batteryLevel = batteryLevel;
        this.droneSerialNo = droneSerialNo;
    }
}
