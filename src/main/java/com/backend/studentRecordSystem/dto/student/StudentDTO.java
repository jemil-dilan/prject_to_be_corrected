package com.backend.studentRecordSystem.dto.student;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public record StudentDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String placeOfBirth,
        Gender gender,
        StudentStatus status,
        LocalDateTime admissionDate,
        Long classId,
        Set<Long> parentIds
) {
}
