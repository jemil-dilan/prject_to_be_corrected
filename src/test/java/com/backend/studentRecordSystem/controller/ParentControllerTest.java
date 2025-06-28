package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.domain.enums.Relationship;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.service.parent.ParentService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class ParentControllerTest {

    @MockitoBean
    private ParentService parentService;


    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    public void getAllParentTest() {
        ParentDTO parentDTO = mock(ParentDTO.class);
        ParentDTO parentDTO1 = mock(ParentDTO.class);
        ParentDTO parentDTO2 = mock(ParentDTO.class);

        when(parentService.getAllParents()).thenReturn(List.of(parentDTO, parentDTO1, parentDTO2));

        when()
                .get("/parents")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).body("$", hasSize(3));

        verify(parentService, times(1)).getAllParents();
    }

    @Test
    public void createParentTest() {
        CreateParentDTO createParentDTO = new CreateParentDTO(
                "John", "Doe", "123456789", "123 Main St",
                Relationship.FATHER, "987654321", "john@example.com", "Engineer"
        );

        ParentDTO parentDTO = ParentDTO.builder()
                .id(1L)
                .firstName(createParentDTO.firstName())
                .lastName(createParentDTO.lastName())
                .phoneNumber(createParentDTO.phoneNumber())
                .address(createParentDTO.address())
                .relationship(createParentDTO.relationship())
                .alternativeContact(createParentDTO.alternativeContact())
                .email(createParentDTO.email())
                .occupation(createParentDTO.occupation())
                .build();

        when(parentService.createParent(createParentDTO)).thenReturn(parentDTO);

        given()
                .contentType(ContentType.JSON)
                .body(createParentDTO)
                .when()
                .post("/parents")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", equalTo(parentDTO.id().intValue()))
                .body("firstName", equalTo(parentDTO.firstName()))
                .body("lastName", equalTo(parentDTO.lastName()))
                .body("phoneNumber", equalTo(parentDTO.phoneNumber()))
                .body("address", equalTo(parentDTO.address()))
                .body("relationship", equalTo(parentDTO.relationship().toString()))
                .body("alternativeContact", equalTo(parentDTO.alternativeContact()))
                .body("email", equalTo(parentDTO.email()))
                .body("occupation", equalTo(parentDTO.occupation()));

        verify(parentService, times(1)).createParent(createParentDTO);
    }

    @Test
    public void getParentByIdTest() {
        ParentDTO parentDTO = new ParentDTO(
                1L, "John", "Doe", "123456789", "123 Main St",
                Relationship.FATHER, "987654321", "john@example.com", "Engineer"
        );

        when(parentService.getParentById(parentDTO.id())).thenReturn(parentDTO);

        when()
                .get("/parents/{id}", 1L)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(parentDTO.id().intValue()))
                .body("firstName", equalTo(parentDTO.firstName()))
                .body("lastName", equalTo(parentDTO.lastName()))
                .body("phoneNumber", equalTo(parentDTO.phoneNumber()))
                .body("address", equalTo(parentDTO.address()))
                .body("relationship", equalTo(parentDTO.relationship().toString()))
                .body("alternativeContact", equalTo(parentDTO.alternativeContact()))
                .body("email", equalTo(parentDTO.email()))
                .body("occupation", equalTo(parentDTO.occupation()));

        verify(parentService, times(1)).getParentById(parentDTO.id());
    }

    @Test
    public void updateParentTest() {
        long parentId = 1L;
        CreateParentDTO createParentDTO = new CreateParentDTO(
                "John", "Doe", "123456789", "123 Main St",
                Relationship.FATHER, "987654321", "john@example.com", "Engineer"
        );

        doNothing().when(parentService).updateParent(parentId, createParentDTO);

        given()
                .contentType(ContentType.JSON)
                .body(createParentDTO)
                .when()
                .put("/parents/{id}", parentId)
                .then()
                .statusCode(204);

        verify(parentService, times(1)).updateParent(parentId, createParentDTO);
    }

    @Test
    public void deleteParentTest() {
        long parentId = 1L;

        doNothing().when(parentService).deleteParent(parentId);

        when()
                .delete("/parents/{id}", parentId)
                .then()
                .statusCode(204);

        verify(parentService, times(1)).deleteParent(parentId);
    }
}