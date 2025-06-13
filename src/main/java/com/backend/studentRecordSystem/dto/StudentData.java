package com.backend.studentRecordSystem.dto;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;

import java.time.LocalDate;

public record StudentData(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String placeOfBirth,
        Gender gender,
        StudentStatus status
//        SchoolClass classId,
//        Set<Parent> parentIds
) {
}
