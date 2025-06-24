package com.backend.studentRecordSystem.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "c_bus_driver")
public class BusDriver extends Staff{
    @Column(name = "c_driver_license_number", nullable = false)
    private String licenseNumber;
    @OneToOne
    @JoinColumn(name = "c_bus_id", nullable = false)
    private Bus assignedBus;
}
