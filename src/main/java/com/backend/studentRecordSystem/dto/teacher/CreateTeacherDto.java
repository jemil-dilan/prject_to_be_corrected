package com.backend.studentRecordSystem.dto.teacher;

import com.backend.studentRecordSystem.domain.enums.Gender;
import com.backend.studentRecordSystem.domain.enums.StaffStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.backend.studentRecordSystem.domain.Teacher}
 */
public record CreateTeacherDto(String firstName, String lastName, String phone_Number, String email, String address,
                               LocalDate date_Of_Birth, String id_Number, Gender gender, BigDecimal salary,
                               LocalDate hire_Date, StaffStatus status, List<List<String>> certificates,
                               int yearsOfExperience) implements Serializable {
}