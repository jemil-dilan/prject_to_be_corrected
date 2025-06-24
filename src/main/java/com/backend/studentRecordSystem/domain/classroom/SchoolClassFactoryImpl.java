package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.dto.ClassroomData;
import com.backend.studentRecordSystem.exception.ConflictException;
import com.backend.studentRecordSystem.exception.ResourceNotFoundException;
import com.backend.studentRecordSystem.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SchoolClassFactoryImpl implements SchoolClassFactory {
    private final SchoolClassRepository schoolClassRepository;

    @Override
    public SchoolClass createClassroom(ClassroomData classroomData) {
        if(schoolClassRepository.existsByClassName(classroomData.className())){
            throw new ConflictException("Classroom with name: " + classroomData.className() + " already exists");
        }
        return schoolClassRepository.save(SchoolClass.builder().
                className(classroomData.className()).
                academicYear(classroomData.academicYear()).
                section(classroomData.section()).
                teacher(classroomData.teacher()).
                students(classroomData.students()).
                build());
    }

    @Override
    public List<SchoolClass> getAllClassrooms() {
        return schoolClassRepository.findAll();
    }

    @Override
    public SchoolClass getClassroomById(long classRoomId) {
        return schoolClassRepository.findById(classRoomId).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Classroom with id: " + classRoomId + " doesn't exists"
                ));
    }

    @Override
    public SchoolClass getClassroomByName(ClassNames className) {
        return schoolClassRepository.findByClassName(className).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Classroom with name: " + className + " doesn't exists"
                ));
    }

    @Override
    public void updateClassroom(long classroomId, ClassroomData classroomData) {
        if (schoolClassRepository.existsById(classroomId)){
            schoolClassRepository.save(SchoolClass.builder().
                    className(classroomData.className()).
                    academicYear(classroomData.academicYear()).
                    section(classroomData.section()).
                    teacher(classroomData.teacher()).
                    students(classroomData.students()).
                    build());
        } else {
            throw new ResourceNotFoundException(
                    "Classroom with id: " + classroomId + " doesn't exists");
        }
    }

    @Override
    public void deleteClassroom(long classroomId) {
        schoolClassRepository.deleteById(classroomId);
    }
}
