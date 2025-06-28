package com.backend.studentRecordSystem.repository;

import com.backend.studentRecordSystem.domain.staff.Staff;

import java.util.UUID;

public interface StaffJpaRepository extends SpringRepository<Staff, UUID> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByIdNumber(String idNumber);

}
