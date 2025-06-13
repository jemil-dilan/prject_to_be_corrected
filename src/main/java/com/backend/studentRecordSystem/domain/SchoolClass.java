package com.backend.studentRecordSystem.domain;

import com.backend.studentRecordSystem.domain.enums.AcademicYear;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
 @Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_class_id", nullable = false)
    private Long id;
    @Column(name = "c_class_name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ClassNames className;
    @Column(name = "c_academic_year", nullable = false)
    @Enumerated(EnumType.STRING)
    private AcademicYear academicYear;
    @OneToOne
    @JoinColumn(name = "c_teacher_id")
    private Teacher teacher;
    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Student> students = new ArrayList<>();
    @Column(name = "c_class_capacity")
    @Builder.Default
    private int maximumStudents = 50;
    @Column(name = "c_available_seats")
    private int availableSeats ;
    @Column(name = "c_section" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private Section section;

    public void addStudent(Student student) {
        if (students.size() < maximumStudents) {
            students.add(student);
            student.setSchoolClass(this);
            availableSeats = maximumStudents - students.size();
        } else {
            throw new IllegalStateException("Class is full, cannot add more students.");
        }
    }

    public void removeStudent(Student student) {
        if (students.remove(student)) {
            student.setSchoolClass(null);
            availableSeats = maximumStudents - students.size();
        } else {
            throw new IllegalStateException("Student not found in this class.");
        }
    }
}
