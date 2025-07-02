package com.backend.studentRecordSystem.domain.staff;

import com.backend.studentRecordSystem.repository.StaffJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class StaffRepositoryImpl implements StaffRepository {
    private final StaffJpaRepository staffJpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return staffJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return staffJpaRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        return staffJpaRepository.existsByIdNumber(idNumber);
    }

    @Override
    public List<Staff> findAll() {
        return staffJpaRepository.findAll();
    }

    @Override
    public void deleteById(UUID staffId) {
        staffJpaRepository.deleteById(staffId);
    }
}
