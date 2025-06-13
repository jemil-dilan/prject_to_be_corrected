package com.backend.studentRecordSystem.service;

import com.backend.studentRecordSystem.domain.Parent;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.dto.ParentData;
import com.backend.studentRecordSystem.factory.parent.ParentFactory;
import com.backend.studentRecordSystem.mapper.ParentMapper;
import com.backend.studentRecordSystem.service.parent.ParentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParentServiceImplTest {
    @Mock
    private ParentFactory parentFactory;

    @Mock
    private ParentMapper parentMapper;

    @InjectMocks
    private ParentServiceImpl objectUnderTest;

    @Test
    void getAllParents() {
        Parent parent = mock();
        Parent parent2 = mock();
        Parent parent3 = mock();
        ParentDTO parentDTO = mock();
        ParentDTO parentDTO2 = mock();
        ParentDTO parentDTO3 = mock();

        when(parentFactory.getAllStudents()).thenReturn(List.of(parent, parent2, parent3));
        when(parentMapper.toDto(parent)).thenReturn(parentDTO);
        when(parentMapper.toDto(parent2)).thenReturn(parentDTO2);
        when(parentMapper.toDto(parent3)).thenReturn(parentDTO3);

        List<ParentDTO> resultUnderTest = objectUnderTest.getAllParents();

        assertThat(resultUnderTest)
                .hasSize(3)
                .contains(parentDTO, parentDTO2, parentDTO3)
                .startsWith(parentDTO)
                .endsWith(parentDTO3);
    }

    @Test
    void getParentById() {
        long parentId = 1L;
        Parent parent = mock(Parent.class);
        ParentDTO parentDTO = mock(ParentDTO.class);

        when(parentFactory.getStudentById(parentId)).thenReturn(parent);
        when(parentMapper.toDto(parent)).thenReturn(parentDTO);

        ParentDTO resultUnderTest = objectUnderTest.getParentById(parentId);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(parentDTO);
    }

    @Test
    void deleteParent() {
        long parentId = 1L;

        doNothing().when(parentFactory).deleteStudent(parentId);

        objectUnderTest.deleteParent(parentId);

        verify(parentFactory, times(1)).deleteStudent(parentId);
    }

    @Test
    void createParent() {
        Parent parent = mock(Parent.class);
        CreateParentDTO createParentDTO = mock(CreateParentDTO.class);
        ParentData parentData = mock(ParentData.class);
        ParentDTO parentDTO = mock(ParentDTO.class);

        when(parentMapper.toData(createParentDTO)).thenReturn(parentData);
        when(parentFactory.createStudent(parentData)).thenReturn(parent);
        when(parentMapper.toDto(parent)).thenReturn(parentDTO);

        ParentDTO resultUnderTest = objectUnderTest.createParent(createParentDTO);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(parentDTO);
    }

    @Test
    void updateParent() {
        long parentId = 1L;
        CreateParentDTO createParentDTO = mock(CreateParentDTO.class);
        ParentData parentData = mock(ParentData.class);

        when(parentMapper.toData(createParentDTO)).thenReturn(parentData);

        doNothing().when(parentFactory).updateStudent(parentId, parentData);

        objectUnderTest.updateParent(parentId, createParentDTO);

        verify(parentFactory, times(1)).updateStudent(parentId, parentData);
    }
}