package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.exception.ConflictException;
import com.backend.studentRecordSystem.exception.ResourceNotFoundException;
import com.backend.studentRecordSystem.repository.ClassroomSpringRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SchoolClassFactoryImpl implements SchoolClassFactory {
    private final ClassroomSpringRepository classroomSpringRepository;

    @Override
    public SchoolClass createClassroom(ClassroomData classroomData) {
        if(classroomSpringRepository.existsByClassName(classroomData.className())){
            throw new ConflictException("Classroom with name: " + classroomData.className() + " already exists");
        }
        return classroomSpringRepository.save(SchoolClass.builder().
                className(classroomData.className()).
                academicYear(classroomData.academicYear()).
                section(classroomData.section()).
                teacher(classroomData.teacher()).
                students(classroomData.students()).
                build());
    }

    @Override
    public List<SchoolClass> getAllClassrooms() {
        return classroomSpringRepository.findAll();
    }

    @Override
    public SchoolClass getClassroomById(long classRoomId) {
        return classroomSpringRepository.findById(classRoomId).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Classroom with id: " + classRoomId + " doesn't exists"
                ));
    }

    @Override
    public SchoolClass getClassroomByName(ClassNames className) {
        return classroomSpringRepository.findByClassName(className).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Classroom with name: " + className + " doesn't exists"
                ));
    }

    @Override
    public void updateClassroom(long classroomId, ClassroomData classroomData) {
        if (classroomSpringRepository.existsById(classroomId)){
            classroomSpringRepository.save(SchoolClass.builder().
                    id(classroomId).
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
        classroomSpringRepository.deleteById(classroomId);
    }
}
