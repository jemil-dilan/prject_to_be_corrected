package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.Parent;

public interface ParentRepository extends SpringRepository<Parent, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
