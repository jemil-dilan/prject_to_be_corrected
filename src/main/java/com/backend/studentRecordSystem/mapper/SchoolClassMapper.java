package com.backend.studentRecordSystem.mapper;

import com.backend.studentRecordSystem.domain.SchoolClass;
import com.backend.studentRecordSystem.domain.Student;
import com.backend.studentRecordSystem.domain.Teacher;
import com.backend.studentRecordSystem.dto.ClassroomData;
import com.backend.studentRecordSystem.dto.classroom.CreateSchoolClassDTO;
import com.backend.studentRecordSystem.dto.classroom.SchoolClassDetailedDTO;
import com.backend.studentRecordSystem.dto.classroom.SchoolClassSummaryDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SchoolClassMapper {

    @Mapping(target = "teacher", source = "teacherId")
    @Mapping(target = "students", source = "studentIds")
    ClassroomData toData(CreateSchoolClassDTO createSchoolClassDTO);

    SchoolClassSummaryDTO toDTO(SchoolClass schoolClass);


    default Student map(UUID id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }

    default List<Student> map(List<UUID> value) {
        if (value == null) {
            return null;
        }
        return value.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


    default Teacher map(Long teacherId) {
        if (teacherId == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        return teacher;
    }

}
