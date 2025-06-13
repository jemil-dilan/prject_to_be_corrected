package com.backend.studentRecordSystem.dto.classroom;

import com.backend.studentRecordSystem.domain.enums.AcademicYear;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Section;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.backend.studentRecordSystem.domain.SchoolClass}
 */
public record SchoolClassDetailedDTO(Long id, ClassNames className, AcademicYear academicYear, Long teacherId,
                                     List<UUID> studentIds, int maximumStudents, int availableSeats,
                                     Section section) implements Serializable {
}