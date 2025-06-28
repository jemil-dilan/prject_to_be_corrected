package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record TeacherData(
        String firstName, String lastName, String phoneNumber, String email, String address,
        LocalDate dateOfBirth, String idNumber, Gender gender, BigDecimal salary,
        StaffStatus status,
        int yearsOfExperience, SchoolClass assignedClass
) {
}
