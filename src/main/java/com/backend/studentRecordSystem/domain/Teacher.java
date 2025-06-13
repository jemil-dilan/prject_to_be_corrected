package com.backend.studentRecordSystem.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Table(name = "t_teachers")
public class Teacher extends Staff {
    @Column(name = "c_teacher_certificates")
    private List<String> certificates;
    @Column(name = "c_years_of_expression")
    private int yearsOfExperience;
    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    private SchoolClass assignedClass;
}
