package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.domain.enums.Relationship;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.service.parent.ParentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ParentController.class)
class ParentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ParentService parentService;
    private final String BASE_URL = "/parents";

    @Test
    void getAllParentsTest() throws Exception{

        ParentDTO parentDTO = mock();
        ParentDTO parentDTO1 = mock();
        ParentDTO parentDTO2 = mock();

        when(parentService.getAllParents())
                .thenReturn(List.of(parentDTO, parentDTO1, parentDTO2));

        mockMvc.perform(get(BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(3)));

        verify(parentService, times(1)).getAllParents();
    }

    @Test
    void getParentsByIDTest() throws Exception {

        Long parentId = 1L;
        ParentDTO parentDTO = new ParentDTO(
                1L,"John", "Doe", "123456789", "123 Main St",
                Relationship.FATHER, "987654321", "john@example.com", "Engineer"
        );
        when(parentService.getParentById(parentId)).thenReturn(parentDTO);

        mockMvc.perform(get(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(parentDTO.id()))
                .andExpect(jsonPath("$.firstName").value(parentDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(parentDTO.lastName()))
                .andExpect(jsonPath("$.email").value(parentDTO.email()))
                .andExpect(jsonPath("$.phoneNumber").value(parentDTO.phoneNumber()))
                .andExpect(jsonPath("$.address").value(parentDTO.address()))
                .andExpect(jsonPath("$.relationship").value(parentDTO.relationship().toString()))
                .andExpect(jsonPath("$.occupation").value(parentDTO.occupation()))
                .andExpect(jsonPath("$.alternativeContact").value(parentDTO.alternativeContact()));

        verify(parentService).getParentById(parentId);
    }

    @Test
    void deleteParentTest() throws Exception {
        Long parentId = 2L;

        doNothing().when(parentService).deleteParent(parentId);

        mockMvc.perform(delete(BASE_URL + "/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(parentService, times(1)).deleteParent(parentId);
    }

    @Test
    void createParentTest() throws Exception {
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

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createParentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(parentDTO.id()))
                .andExpect(jsonPath("$.firstName").value(parentDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(parentDTO.lastName()))
                .andExpect(jsonPath("$.email").value(parentDTO.email()))
                .andExpect(jsonPath("$.phoneNumber").value(parentDTO.phoneNumber()))
                .andExpect(jsonPath("$.address").value(parentDTO.address()))
                .andExpect(jsonPath("$.relationship").value(parentDTO.relationship().toString()))
                .andExpect(jsonPath("$.occupation").value(parentDTO.occupation()))
                .andExpect(jsonPath("$.alternativeContact").value(parentDTO.alternativeContact()));

        verify(parentService).createParent(createParentDTO);
    }

    @Test
    void updateParentTest() throws Exception {
        Long parentId = 2L;
        CreateParentDTO createParentDTO = new CreateParentDTO(
                "John", "Doe", "123456789", "123 Main St",
                Relationship.FATHER, "987654321", "john@example.com", "Engineer"
        );


        doNothing().when(parentService).updateParent(parentId, createParentDTO);

        mockMvc.perform(put(BASE_URL + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createParentDTO)))
                .andExpect(status().isNoContent());

        verify(parentService).updateParent(parentId,createParentDTO);
    }
}