package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import com.backend.studentRecordSystem.domain.enums.ClassNames;

import java.util.Optional;

public interface SchoolClassRepository extends SpringRepository<SchoolClass, Long> {
    Boolean existsByClassName(ClassNames className);

    Optional<SchoolClass> findByClassName(ClassNames className);
}