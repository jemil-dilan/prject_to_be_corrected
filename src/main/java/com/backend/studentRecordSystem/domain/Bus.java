package com.backend.studentRecordSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_bus_id", nullable = false)
    private Long id;
    @Column(name = "c_bus_model", nullable = false)
    private String model;
    @Column(name = "c_make_year", nullable = false)
    private int makeYear;
    @Column(name = "c_plate_number", nullable = false, unique = true)
    private String plateNumber;
    @Column(name = "c_bus_capacity", nullable = false)
    private int capacity;
    @Column(name = "c_route_info", nullable = false)
    private String routeInfo;
    @OneToOne
    @JoinColumn(name = "c_driver_licence_number")
    private BusDriver driver;

}
