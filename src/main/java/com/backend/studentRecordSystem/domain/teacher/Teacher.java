package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.domain.staff.Staff;
import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Table(name = "t_teachers")
public class Teacher extends Staff {
//    @Column(name = "c_teacher_certificates")
//    private List<String> certificates;
    @Column(name = "c_years_of_expression")
    private Integer yearsOfExperience;
    @OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
    private SchoolClass assignedClass;
}
