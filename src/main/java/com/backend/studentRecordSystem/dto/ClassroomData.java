package com.backend.studentRecordSystem.dto;

import com.backend.studentRecordSystem.domain.Student;
import com.backend.studentRecordSystem.domain.Teacher;
import com.backend.studentRecordSystem.domain.enums.AcademicYear;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Section;

import java.util.List;

public record ClassroomData(Long id,
                            ClassNames className,
                            AcademicYear academicYear,
                            Teacher teacher,
                            List<Student> students,
                            Section section) {
}
