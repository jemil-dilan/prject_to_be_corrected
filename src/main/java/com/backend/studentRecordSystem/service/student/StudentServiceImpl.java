package com.backend.studentRecordSystem.service.student;

import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.domain.student.StudentFactory;
import com.backend.studentRecordSystem.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentMapper studentMapper;
    private final StudentFactory studentFactory;

    public StudentServiceImpl(StudentMapper studentMapper, StudentFactory studentFactory) {
        this.studentMapper = studentMapper;
        this.studentFactory = studentFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return studentFactory.getAllStudents().stream().map(studentMapper::toStudentDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(UUID id) {
        return studentMapper.toStudentDTO(studentFactory.getStudentById(id));
    }

    @Override
    public void deleteStudent(UUID id) {
        studentFactory.deleteStudent(id);
    }

    @Override
    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
        return studentMapper.toStudentDTO(
                studentFactory.createStudent(
                        studentMapper.toStudentData(createStudentDTO))
        );
    }

    @Override
    public void updateStudent(UUID id, CreateStudentDTO createStudentDTO) {
        studentFactory.updateStudent(
                id,
                studentMapper.toStudentData(createStudentDTO)
        );
    }

    @Override
    public void clearDatabase() {
        studentFactory.clearDatabase();
    }
}
