package com.backend.studentRecordSystem.repository;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public boolean existsByFirstNameAndLastName(String firstName, String lastName) {
        return false;
    }
}
