package com.backend.studentRecordSystem.domain.staff;

import java.util.List;
import java.util.UUID;

public interface StaffRepository {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByIdNumber(String idNumber);

    List<Staff> findAll();

    void deleteById(UUID staffId);

}
