package com.backend.studentRecordSystem.mapper;

import com.backend.studentRecordSystem.domain.teacher.CreateTeacherRequest;
import com.backend.studentRecordSystem.domain.teacher.Teacher;
import com.backend.studentRecordSystem.domain.teacher.TeacherResponse;
import com.backend.studentRecordSystem.domain.teacher.TeacherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "assignedClassClassName", source = "assignedClass.className")
    TeacherResponse toTeacherDTO(Teacher teacher);

    TeacherData toTeacherData(CreateTeacherRequest createTeacherRequest);
}
