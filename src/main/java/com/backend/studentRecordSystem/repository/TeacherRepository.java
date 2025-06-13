package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends SpringRepository<Teacher, Long> {
}