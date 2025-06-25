package com.backend.studentRecordSystem.domain.student;

import com.backend.studentRecordSystem.dto.StudentData;
import com.backend.studentRecordSystem.exception.ConflictException;
import com.backend.studentRecordSystem.exception.ResourceNotFoundException;
import com.backend.studentRecordSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class StudentFactoryImpl implements  StudentFactory {
    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(StudentData studentData) {
        if (studentRepository.existsByFirstNameAndLastName(
                studentData.firstName(),
                studentData.lastName())){
            throw new ConflictException("Student with name: " + studentData.firstName()
                                        + " " + studentData.lastName() + " already exists");
        }
        return studentRepository.save(
                Student.builder()
                        .firstName(studentData.firstName())
                        .lastName(studentData.lastName())
                        .dateOfBirth(studentData.dateOfBirth())
                        .placeOfBirth(studentData.placeOfBirth())
                        .gender(studentData.gender())
                        .status(studentData.status())
//                        .schoolClass(studentData.classId())
//                        .parents(studentData.parentIds())
                        .build()
        );
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(UUID id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id: " + id + " was not found"));
    }

    @Override
    public void updateStudent(UUID id, StudentData studentData) {
        if (studentRepository.existsById(id)){
            studentRepository.save(
                    Student.builder()
                            .id(id)
                            .firstName(studentData.firstName())
                            .lastName(studentData.lastName())
                            .dateOfBirth(studentData.dateOfBirth())
                            .placeOfBirth(studentData.placeOfBirth())
                            .gender(studentData.gender())
                            .status(studentData.status())
//                            .schoolClass(studentData.classId())
//                            .parents(studentData.parentIds())
                            .build()
            );
        }
        else {
            throw new ResourceNotFoundException("Student with id: " + id + " was not found");
        }
    }

    @Override
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void clearDatabase() {
        studentRepository.deleteAll();
    }
}
