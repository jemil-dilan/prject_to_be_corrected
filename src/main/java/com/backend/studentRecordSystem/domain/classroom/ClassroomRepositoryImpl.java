package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.repository.ClassroomSpringRepository;

import java.util.List;
import java.util.Optional;

public class ClassroomRepositoryImpl implements com.backend.studentRecordSystem.domain.classroom.ClassroomRepository {
    private final ClassroomSpringRepository classroomJpaRepository;

    public ClassroomRepositoryImpl(ClassroomSpringRepository classroomJpaRepository) {
        this.classroomJpaRepository = classroomJpaRepository;
    }

    @Override
    public SchoolClass save(SchoolClass classroom) {
        return classroomJpaRepository.save(classroom);
    }

    @Override
    public List<SchoolClass> findAll() {
        return classroomJpaRepository.findAll();
    }

    @Override
    public Optional<SchoolClass> findById(Long id) {
        return classroomJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        classroomJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return classroomJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        classroomJpaRepository.deleteAll();
    }

    @Override
    public Boolean existsByClassName(ClassNames className) {
        return classroomJpaRepository.existsByClassName(className);
    }

    @Override
    public Optional<SchoolClass> findByClassName(ClassNames className) {
        return classroomJpaRepository.findByClassName(className);
    }
}
