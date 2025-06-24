package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentJpaRepository extends JpaRepository<Student, UUID> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
