package com.backend.studentRecordSystem.domain.parent;

import com.backend.studentRecordSystem.dto.ParentData;
import com.backend.studentRecordSystem.exception.ConflictException;
import com.backend.studentRecordSystem.exception.ResourceNotFoundException;
import com.backend.studentRecordSystem.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class ParentFactoryImpl implements ParentFactory {
    private final ParentRepository parentRepository;
    @Override
    public Parent createParent(ParentData parentData) {
        if (parentRepository.existsByPhoneNumber(parentData.phoneNumber())) {
            throw new ConflictException("Student with name: " + parentData.firstName()
                    + " " + parentData.lastName() + " already exists");
        }
        return parentRepository.save(
                Parent.builder()
                        .firstName(parentData.firstName())
                        .lastName(parentData.lastName())
                        .phoneNumber(parentData.phoneNumber())
                        .address(parentData.address())
                        .relationship(parentData.relationship())
                        .alternativeContact(parentData.alternativeContact())
                        .email(parentData.email())
                        .occupation(parentData.occupation())
                        .build()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Parent getParentById(Long id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent with id: " + id + " was not found"));
    }

    @Override
    public void updateParent(Long id, ParentData parentData) {
        if (parentRepository.existsById(id)) {
            parentRepository.save(
                    Parent.builder()
                            .id(id)
                            .firstName(parentData.firstName())
                            .lastName(parentData.lastName())
                            .phoneNumber(parentData.phoneNumber())
                            .address(parentData.address())
                            .relationship(parentData.relationship())
                            .alternativeContact(parentData.alternativeContact())
                            .email(parentData.email())
                            .occupation(parentData.occupation())
                            .build()
            );
        } else {
            throw new ResourceNotFoundException("Parent with id: " + id + " was not found");
        }
    }

    @Override
    public void deleteParent(Long id) {
            parentRepository.deleteById(id);
    }

    @Override
    public void clearDatabase() {
        parentRepository.deleteAll();
    }
}
