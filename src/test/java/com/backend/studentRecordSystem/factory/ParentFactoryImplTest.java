package com.backend.studentRecordSystem.factory;

import com.backend.studentRecordSystem.domain.parent.Parent;
import com.backend.studentRecordSystem.dto.ParentData;
import com.backend.studentRecordSystem.domain.parent.ParentFactoryImpl;
import com.backend.studentRecordSystem.domain.parent.ParentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParentFactoryImplTest {
    @Mock
    private ParentRepository parentRepository;

    @InjectMocks
    private ParentFactoryImpl objectUnderTest;

    @Test
    void createParentTest() {

        ParentData parentData = mock(ParentData.class);
        Parent parent = Parent.builder()
                .id(parentData.id())
                .firstName(parentData.firstName())
                .lastName(parentData.lastName())
                .phoneNumber(parentData.phoneNumber())
                .address(parentData.address())
                .relationship(parentData.relationship())
                .alternativeContact(parentData.alternativeContact())
                .email(parentData.email())
                .occupation(parentData.occupation())
                .build();

        when(parentRepository.existsByPhoneNumber(parentData.phoneNumber())).thenReturn(false);
        when(parentRepository.save(any(Parent.class))).thenReturn(parent);

        Parent resultUnderTest = objectUnderTest.createParent(parentData);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(parent);
    }

    @Test
    void getAllParentsTest() {

        Parent parent = mock(Parent.class);
        Parent parent1 = mock(Parent.class);
        Parent parent2 = mock(Parent.class);

        when(parentRepository.findAll()).thenReturn(List.of(parent, parent1, parent2));

        List<Parent> resultUnderTest = objectUnderTest.getAllParents();

        assertThat(resultUnderTest)
                .hasSize(3)
                .contains(parent, parent1, parent2)
                .startsWith(parent)
                .endsWith(parent2);
    }

    @Test
    void getParentByIdTest() {

        long parentId = 1L;
        Parent parent = mock(Parent.class);

        when(parentRepository.findById(parentId)).thenReturn(Optional.of(parent));

        Parent resultUnderTest = objectUnderTest.getParentById(parentId);

        assertThat(resultUnderTest)
                .usingRecursiveComparison()
                .isEqualTo(parent);
    }

    @Test
    void updateParentTest() {

        long parentId = 1L;
        ParentData parentData = mock(ParentData.class);
        Parent parent = mock(Parent.class);

        when(parentRepository.existsById(parentId)).thenReturn(true);
        when(parentRepository.save(any(Parent.class))).thenReturn(parent);

        objectUnderTest.updateParent(parentId, parentData);

        assertThat(parent)
                .usingRecursiveComparison()
                .isEqualTo(
                        Parent.builder()
                                .id(parentData.id())
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

    @Test
    void deleteParentTest() {

        long parentId = 1L;

        objectUnderTest.deleteParent(parentId);

        assertThat(parentRepository.existsById(parentId)).isFalse();
    }
}