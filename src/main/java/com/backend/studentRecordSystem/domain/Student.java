package com.backend.studentRecordSystem.domain;


import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "c_student_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "c_student_first_name")
    private  String firstName;
    @Column(name = "c_student_last_name")
    private String lastName;
    @Column(name = "c_student_date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "c_student_place_of_birth")
    private String placeOfBirth;
    @Column(name = "c_student_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "c_student_status")
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
    @Column(name = "c_student_admission_date")
    @Builder.Default
    private LocalDateTime admissionDate = LocalDateTime.now();
    @Column(name = "c_student_updated_date")
    @Builder.Default
    private LocalDateTime updatedDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "c_class_id")
    private SchoolClass schoolClass;
    @ManyToMany
    @JoinTable(
            name = "student_parent",
            joinColumns = @JoinColumn(name = "c_student_id"),
            inverseJoinColumns = @JoinColumn(name = "c_parent_id")
    )
    @Builder.Default
    private Set<Parent> parents = new HashSet<>();
}
