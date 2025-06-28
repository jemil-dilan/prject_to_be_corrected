package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffRole;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Teacher}
 */
@Builder
public record TeacherResponse(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Gender gender,
        StaffRole staffRole,
        ClassNames assignedClassClassName
) implements Serializable {
}