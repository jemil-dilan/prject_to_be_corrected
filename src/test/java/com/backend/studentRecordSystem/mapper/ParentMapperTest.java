package com.backend.studentRecordSystem.mapper;

import com.backend.studentRecordSystem.domain.Parent;
import com.backend.studentRecordSystem.domain.enums.Relationship;
import com.backend.studentRecordSystem.dto.ParentData;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParentMapperTest {
    private final ParentMapper objectUnderTest = Mappers.getMapper(ParentMapper.class);

    @Test
    void toDtoTest() {
        Parent parent = Parent.builder().
                id(1L).
                firstName("John").
                lastName("Doe").
                email("email@yo.com").
                phoneNumber("123456789").
                address("123 Main St").
                relationship(Relationship.FATHER).
                occupation("Engineer").
                alternativeContact("987654321").
                build();

        ParentDTO resultUnderTest = objectUnderTest.toDto(parent);

        assertThat(resultUnderTest).
                isNotNull().
                isInstanceOf(ParentDTO.class).
                hasFieldOrPropertyWithValue("id", 1L).
                hasFieldOrPropertyWithValue("firstName", "John").
                hasFieldOrPropertyWithValue("lastName", "Doe").
                hasFieldOrPropertyWithValue("email", "email@yo.com").
                hasFieldOrPropertyWithValue("phoneNumber", "123456789").
                hasFieldOrPropertyWithValue("address", "123 Main St").
                hasFieldOrPropertyWithValue("relationship", Relationship.FATHER).
                hasFieldOrPropertyWithValue("occupation", "Engineer").
                hasFieldOrPropertyWithValue("alternativeContact", "987654321");
    }

    @Test
    void toDataTest() {

        CreateParentDTO createParentDTO = CreateParentDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .address("123 Main St")
                .relationship(Relationship.FATHER)
                .alternativeContact("987654321")
                .email("email.yo.com")
                .occupation("Engineer")
                .build();

        ParentData resultUnderTest = objectUnderTest.toData(createParentDTO);

        assertThat(resultUnderTest)
                .isNotNull()
                .isInstanceOf(ParentData.class)
                .hasFieldOrPropertyWithValue("firstName", "John")
                .hasFieldOrPropertyWithValue("lastName", "Doe")
                .hasFieldOrPropertyWithValue("phoneNumber", "123456789")
                .hasFieldOrPropertyWithValue("address", "123 Main St")
                .hasFieldOrPropertyWithValue("relationship", Relationship.FATHER)
                .hasFieldOrPropertyWithValue("alternativeContact", "987654321")
                .hasFieldOrPropertyWithValue("email", "email.yo.com")
                .hasFieldOrPropertyWithValue("occupation", "Engineer");
    }
}