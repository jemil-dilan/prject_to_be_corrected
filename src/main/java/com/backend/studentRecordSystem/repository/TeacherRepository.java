package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.Teacher;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends SpringRepository<Teacher, UUID> {
}