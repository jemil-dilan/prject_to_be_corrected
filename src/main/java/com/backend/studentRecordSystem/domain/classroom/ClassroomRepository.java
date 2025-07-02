package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.parent.Parent;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository {
    SchoolClass save(SchoolClass classroom);
    List<SchoolClass> findAll();
    Optional<SchoolClass> findById(Long id);
    void deleteById( Long id);
    boolean existsById(Long id);
    void deleteAll();
    Boolean existsByClassName(ClassNames className);
    Optional<SchoolClass> findByClassName(ClassNames className);
}
