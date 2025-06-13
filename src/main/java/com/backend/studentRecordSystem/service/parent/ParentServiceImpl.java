package com.backend.studentRecordSystem.service.parent;

import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.factory.parent.ParentFactory;
import com.backend.studentRecordSystem.mapper.ParentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentFactory parentFactory;
    private final ParentMapper parentMapper;
    @Override
    @Transactional(readOnly = true)
    public List<ParentDTO> getAllParents() {
        return parentFactory
                .getAllStudents().stream().map(parentMapper :: toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ParentDTO getParentById(Long id) {
        return parentMapper.toDto(parentFactory.getStudentById(id));
    }

    @Override
    public void deleteParent(Long id) {
        parentFactory.deleteStudent(id);
    }

    @Override
    public ParentDTO createParent(CreateParentDTO createParentDTO) {
        return parentMapper.toDto(
                parentFactory.createStudent(
                        parentMapper.toData(createParentDTO)
                )
        );
    }

    @Override
    public void updateParent(Long id, CreateParentDTO createParentDTO) {
        parentFactory.updateStudent(
                id,
                parentMapper.toData(createParentDTO)
        );
    }

    @Override
    public void clearDatabase() {
        parentFactory.clearDatabase();
    }
}
