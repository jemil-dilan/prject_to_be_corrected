package com.backend.studentRecordSystem.domain.teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository {
    Teacher save(Teacher teacher);
    List<Teacher> findAll();
    Optional<Teacher> findById(UUID id);
    void deleteById( UUID id);
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByPhoneNumber(String phoneNumber);
    boolean existsById(UUID id);

    boolean existsByEmail(String email);
}
