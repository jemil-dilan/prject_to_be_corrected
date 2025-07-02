package com.backend.studentRecordSystem.domain.teacher;

import com.backend.studentRecordSystem.domain.enums.StaffRole;
import com.backend.studentRecordSystem.domain.staff.StaffRepository;
import com.backend.studentRecordSystem.exception.ConflictException;
import com.backend.studentRecordSystem.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TeacherFactoryImpl implements TeacherFactory {
    private final TeacherRepository teacherRepository;
    private final StaffRepository staffRepository;
    @Override
    public Teacher createTeacher(TeacherData teacherData) {
        String conflictKey = "key";
        if(staffRepository.existsByEmail(teacherData.email())){
            conflictKey = "email";
        } else if (staffRepository.existsByPhoneNumber(teacherData.phoneNumber())) {
            conflictKey = "phoneNumber";
        } else if (staffRepository.existsByIdNumber(teacherData.idNumber())) {
            conflictKey = "idNumber";
        }

        return switch (conflictKey) {
            case "email" ->
                    throw new ConflictException("Teacher with email: " + teacherData.email() + " already exists");
            case "phoneNumber" ->
                    throw new ConflictException("Teacher with phone number: " + teacherData.phoneNumber() + " already exists");
            case "idNumber" ->
                    throw new ConflictException("Teacher with id card number: " + teacherData.idNumber() + " already exists");
            default -> teacherRepository.save(
                    Teacher.builder()
                            .firstName(teacherData.firstName())
                            .lastName(teacherData.lastName())
                            .phoneNumber(teacherData.phoneNumber())
                            .email(teacherData.email())
                            .address(teacherData.address())
                            .dateOfBirth(teacherData.dateOfBirth())
                            .idNumber(teacherData.idNumber())
                            .gender(teacherData.gender())
                            .staffRole(StaffRole.TEACHER)
                            .salary(teacherData.salary())
                            .status(teacherData.status())
                            .yearsOfExperience(teacherData.yearsOfExperience())
                            .assignedClass(teacherData.assignedClass())
                            .build()
            );
        };

    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(UUID id) {
        return teacherRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Teacher with id: " + id + " was not found"));
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Teacher with email: " + email + " was not found"));
    }

    @Override
    public Teacher getTeacherByPhoneNumber(String phoneNumber) {
        return teacherRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Teacher with phone number: " +
                                        phoneNumber + " was not found"));
    }

    @Override
    public void updateTeacher(UUID id, TeacherData teacherData) {
        if(!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher with id: " + id + " was not found");
        } else {
            teacherRepository.save(
                Teacher.builder()
                        .id(id)
                        .firstName(teacherData.firstName())
                        .lastName(teacherData.lastName())
                        .phoneNumber(teacherData.phoneNumber())
                        .email(teacherData.email())
                        .address(teacherData.address())
                        .dateOfBirth(teacherData.dateOfBirth())
                        .idNumber(teacherData.idNumber())
                        .gender(teacherData.gender())
                        .staffRole(StaffRole.TEACHER)
                        .salary(teacherData.salary())
                        .status(teacherData.status())
                        .yearsOfExperience(teacherData.yearsOfExperience())
                        .assignedClass(teacherData.assignedClass())
                        .build()
            );
        }
    }

    @Override
    public void deleteTeacher(UUID id) {
        teacherRepository.deleteById(id);
    }
}
