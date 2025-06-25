package com.backend.studentRecordSystem.dto.classroom;

import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import com.backend.studentRecordSystem.domain.enums.AcademicYear;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Section;

import java.io.Serializable;

/**
 * DTO for {@link SchoolClass}
 */
public record SchoolClassSummaryDTO(Long id, ClassNames className, AcademicYear academicYear,
                                    Section section) implements Serializable {
}