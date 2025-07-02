package com.backend.studentRecordSystem.domain.parent;

import java.util.List;
import java.util.Optional;

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
