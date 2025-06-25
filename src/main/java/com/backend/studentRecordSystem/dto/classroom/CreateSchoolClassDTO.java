package com.backend.studentRecordSystem.dto.classroom;

import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import com.backend.studentRecordSystem.domain.enums.AcademicYear;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Section;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link SchoolClass}
 */
public record CreateSchoolClassDTO(ClassNames className, AcademicYear academicYear, UUID teacherId,
                                   List<UUID> studentIds, int maximumStudents, int availableSeats,
                                   Section section) implements Serializable {
}