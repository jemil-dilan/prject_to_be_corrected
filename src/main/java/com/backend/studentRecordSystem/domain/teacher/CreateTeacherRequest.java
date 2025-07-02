package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffStatus;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Teacher}
 */

@Builder
public record CreateTeacherRequest(String firstName,
                                   String lastName,
                                   String phoneNumber,
                                   String email,
                                   String address,
                                   LocalDate dateOfBirth,
                                   String idNumber,
                                   Gender gender,
                                   BigDecimal salary,
                                   StaffStatus status,
                                   int yearsOfExperience,
                                   Long assignedClassId) implements Serializable {
}