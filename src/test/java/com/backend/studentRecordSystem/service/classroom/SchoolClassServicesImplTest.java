package com.backend.studentRecordSystem.service.classroom;

import com.backend.studentRecordSystem.domain.classroom.SchoolClass;
import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.dto.ClassroomData;
import com.backend.studentRecordSystem.dto.classroom.CreateSchoolClassDTO;
import com.backend.studentRecordSystem.dto.classroom.SchoolClassSummaryDTO;
import com.backend.studentRecordSystem.domain.classroom.SchoolClassFactory;
import com.backend.studentRecordSystem.mapper.SchoolClassMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolClassServicesImplTest {
    @Mock
    SchoolClassFactory classroomFactory;

    @Mock
    SchoolClassMapper classroomMapper;

    @InjectMocks
    SchoolClassServicesImpl objectUnderTest;

    @Test
    void createClassroomTest(){
        CreateSchoolClassDTO createSchoolClassDTO = mock(CreateSchoolClassDTO.class);
        ClassroomData classroomData = mock(ClassroomData.class);
        SchoolClass schoolClass = mock(SchoolClass.class);
        SchoolClassSummaryDTO classSummaryDTO = mock(SchoolClassSummaryDTO.class);

        when(classroomMapper.toData(createSchoolClassDTO)).thenReturn(classroomData);
        when(classroomFactory.createClassroom(classroomData)).thenReturn(schoolClass);
        when(classroomMapper.toDTO(schoolClass)).thenReturn(classSummaryDTO);

        SchoolClassSummaryDTO resultUnderTest = objectUnderTest.createClassroom(createSchoolClassDTO);

        assertThat(resultUnderTest).
                isNotNull().
                usingRecursiveComparison().
                isEqualTo(classSummaryDTO);
    }

    @Test
    void getAllClassroomsTest(){

        SchoolClass schoolClass = mock(SchoolClass.class);
        SchoolClass schoolClass1 = mock(SchoolClass.class);
        SchoolClass schoolClass2 = mock(SchoolClass.class);
        SchoolClassSummaryDTO schoolClassSummaryDTO = mock(SchoolClassSummaryDTO.class);
        SchoolClassSummaryDTO schoolClassSummaryDTO1 = mock(SchoolClassSummaryDTO.class);
        SchoolClassSummaryDTO schoolClassSummaryDTO2 = mock(SchoolClassSummaryDTO.class);

        when(classroomFactory.getAllClassrooms()).thenReturn(List.of(schoolClass, schoolClass2, schoolClass1));
        when(classroomMapper.toDTO(schoolClass)).thenReturn(schoolClassSummaryDTO);
        when(classroomMapper.toDTO(schoolClass1)).thenReturn(schoolClassSummaryDTO1);
        when(classroomMapper.toDTO(schoolClass2)).thenReturn(schoolClassSummaryDTO2);

        List<SchoolClassSummaryDTO> resultUnderTest = objectUnderTest.getAllClassrooms();

        assertThat(resultUnderTest).
                hasSize(3).
                contains(schoolClassSummaryDTO, schoolClassSummaryDTO1, schoolClassSummaryDTO2).
                startsWith(schoolClassSummaryDTO).
                endsWith(schoolClassSummaryDTO1);
    }

    @Test
    void getClassroomByIdTest(){
        long classRoomId = 1L;
        SchoolClass schoolClass = mock(SchoolClass.class);
        SchoolClassSummaryDTO schoolClassSummaryDTO = mock(SchoolClassSummaryDTO.class);

        when(classroomFactory.getClassroomById(classRoomId)).thenReturn(schoolClass);
        when(classroomMapper.toDTO(schoolClass)).thenReturn(schoolClassSummaryDTO);

        SchoolClassSummaryDTO resultUnderTest = objectUnderTest.getClassroomById(classRoomId);

        assertThat(resultUnderTest).
                isNotNull().
                usingRecursiveComparison().
                isEqualTo(schoolClassSummaryDTO);
    }

    @Test
    void getClassroomByNameTest(){

        ClassNames className = ClassNames.CLASS_1B;
        SchoolClass schoolClass = mock(SchoolClass.class);
        SchoolClassSummaryDTO schoolClassSummaryDTO = mock(SchoolClassSummaryDTO.class);

        when(classroomFactory.getClassroomByName(className)).thenReturn(schoolClass);
        when(classroomMapper.toDTO(schoolClass)).thenReturn(schoolClassSummaryDTO);

        SchoolClassSummaryDTO resultUnderTest = objectUnderTest.getClassroomByName(className);

        assertThat(resultUnderTest).
                isNotNull().
                usingRecursiveComparison().
                isEqualTo(schoolClassSummaryDTO);
    }

    @Test
    void updateClassroomTest(){

        long classroomId = 1L;
        ClassroomData classroomData = mock(ClassroomData.class);
        CreateSchoolClassDTO createSchoolClassDTO = mock(CreateSchoolClassDTO.class);

        when(classroomMapper.toData(createSchoolClassDTO)).thenReturn(classroomData);
        doNothing().when(classroomFactory).updateClassroom(classroomId, classroomData);

        objectUnderTest.updateClassroom(classroomId, createSchoolClassDTO);

        verify(classroomFactory,times(1)).updateClassroom(classroomId, classroomData);
        verify(classroomMapper,times(1)).toData(createSchoolClassDTO);
    }

    @Test
    void deleteClassroomTest(){
        long classroomId = 1L;

        doNothing().when(classroomFactory).deleteClassroom(classroomId);

        objectUnderTest.deleteClassroom(classroomId);

        verify(classroomFactory).deleteClassroom(classroomId);
    }
}