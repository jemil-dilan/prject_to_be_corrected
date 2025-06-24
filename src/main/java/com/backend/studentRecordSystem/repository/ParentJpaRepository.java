package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.parent.Parent;

public interface ParentJpaRepository extends SpringRepository<Parent, Long>{
    boolean existsByPhoneNumber(String phoneNumber);
}
