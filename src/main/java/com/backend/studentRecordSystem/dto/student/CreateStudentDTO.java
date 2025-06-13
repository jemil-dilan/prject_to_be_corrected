package com.backend.studentRecordSystem.dto.student;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StudentStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateStudentDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String placeOfBirth,
        Gender gender,
        StudentStatus status
//        Long classId,
//        Long parentIds
) {

}
