package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.teacher.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherJpaRepository extends SpringRepository<Teacher, UUID> {
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}