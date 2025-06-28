package com.backend.studentRecordSystem.domain.parent;

import com.backend.studentRecordSystem.repository.ParentJpaRepository;

import java.util.List;
import java.util.Optional;

public class ParentRepositoryImpl implements ParentRepository {
    private final ParentJpaRepository parentJpaRepository;

    public ParentRepositoryImpl(ParentJpaRepository parentJpaRepository) {
        this.parentJpaRepository = parentJpaRepository;
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return parentJpaRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Parent save(Parent parent) {
        return parentJpaRepository.save(parent);
    }

    @Override
    public List<Parent> findAll() {
        return parentJpaRepository.findAll();
    }

    @Override
    public Optional<Parent> findById(Long id) {
        return parentJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        parentJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return parentJpaRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        parentJpaRepository.deleteAll();
    }
}
