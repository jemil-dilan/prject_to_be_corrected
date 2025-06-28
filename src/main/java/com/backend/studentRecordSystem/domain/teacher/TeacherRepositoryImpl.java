package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.repository.TeacherJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {
    private final TeacherJpaRepository teacherJpaRepository;

    @Override
    public Teacher save(Teacher teacher) {
        return teacherJpaRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherJpaRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(UUID id) {
        return teacherJpaRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        teacherJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Teacher> findByEmail(String email) {
        return teacherJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Teacher> findByPhoneNumber(String phoneNumber) {
        return teacherJpaRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsById(UUID id) {
        return teacherJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return teacherJpaRepository.existsByEmail(email);
    }


}
