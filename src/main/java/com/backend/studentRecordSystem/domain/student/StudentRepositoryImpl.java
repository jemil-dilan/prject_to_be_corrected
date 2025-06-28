package com.backend.studentRecordSystem.domain.student;

import com.backend.studentRecordSystem.repository.StudentJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentRepositoryImpl implements StudentRepository {
    private final StudentJpaRepository studentJpaRepository;

    public StudentRepositoryImpl(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public Student save(Student student) {
        return studentJpaRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentJpaRepository.findAll();
    }

    @Override
    public Optional<Student> findById(UUID id) {
        return studentJpaRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
       studentJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return studentJpaRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public boolean existsById(UUID id) {
        return studentJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
         studentJpaRepository.deleteAll();
    }

}
