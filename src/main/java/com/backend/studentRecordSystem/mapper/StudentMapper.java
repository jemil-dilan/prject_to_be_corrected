package com.backend.studentRecordSystem.mapper;

import com.backend.studentRecordSystem.domain.parent.Parent;
import com.backend.studentRecordSystem.domain.student.Student;
import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.dto.StudentData;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "classId", ignore = true)
    @Mapping(target = "parentIds", source = "parents")
    StudentDTO toStudentDTO(Student student);

    StudentData toStudentData(CreateStudentDTO createStudentDTO);


    default Set<Long> mapParentsToIds(Set<Parent> parents) {
        if (parents == null) return new HashSet<>();
        return parents.stream()
                .map(Parent::getId)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "updatedDate", ignore = true)
    Student toEntity(StudentDTO studentDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDTO studentDTO, @MappingTarget Student student);
}
