package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.parent.Parent;
import com.backend.studentRecordSystem.domain.student.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//public interface ParentRepository extends SpringRepository<Parent, Long> {
public interface ParentRepository {
    boolean existsByPhoneNumber(String phoneNumber);

    Parent save(Parent parent);
    List<Parent> findAll();
    Optional<Parent> findById(Long id);
    void deleteById( Long id);
    boolean existsById(Long id);
    void deleteAll();
}
