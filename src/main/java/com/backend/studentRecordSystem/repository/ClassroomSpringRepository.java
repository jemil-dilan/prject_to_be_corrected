package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import com.backend.studentRecordSystem.domain.enums.ClassNames;

import java.util.Optional;

public interface ClassroomSpringRepository extends SpringRepository<SchoolClass, Long> {
    Boolean existsByClassName(ClassNames className);

    Optional<SchoolClass> findByClassName(ClassNames className);
}