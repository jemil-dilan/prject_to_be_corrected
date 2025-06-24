package com.backend.studentRecordSystem.domain;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffRole;
import com.backend.studentRecordSystem.domain.enums.StaffStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "t_staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "c_staff_id", nullable = false)
    private UUID id;
    @Column(name = "c_staff_first_name", nullable = false)
    private String firstName;
    @Column(name = "c_staff_last_name", nullable = false)
    private String lastName;
    @Column(name = "c_staff_phone_number", nullable = false)
    private String phone_Number;
    @Column(name = "c_staff_email", unique = true)
    private String email;
    @Column(name = "c_staff_addrss", nullable = false)
    private String address;
    @Column(name = "STAFF_DATE_OF_BIRTH")
    private LocalDate date_Of_Birth;
    @Column(name = "STAFF_ID_NUMBER", unique = true)
    private String id_Number;
    @Column(name = "STAFF_GENDER")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column(name = "STAFF_ROLE")
    @Enumerated(value = EnumType.STRING)
    private StaffRole staffRole;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "HIRE_DATE")
    private  LocalDate hire_Date;
    @Column(name = "STAFF_STATUS")
    private StaffStatus status;

}
