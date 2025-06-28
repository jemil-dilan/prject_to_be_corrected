package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.student.Student;
import com.backend.studentRecordSystem.domain.teacher.Teacher;
import com.backend.studentRecordSystem.domain.enums.AcademicYear;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.domain.enums.Section;

import java.util.List;
import java.util.UUID;

public record ClassroomData(UUID id,
                            ClassNames className,
                            AcademicYear academicYear,
                            Teacher teacher,
                            List<Student> students,
                            Section section) {
}
