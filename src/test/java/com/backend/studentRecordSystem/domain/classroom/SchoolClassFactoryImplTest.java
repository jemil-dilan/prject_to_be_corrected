package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.dto.ClassroomData;
import com.backend.studentRecordSystem.repository.SchoolClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolClassFactoryImplTest {
    @Mock
    private SchoolClassRepository classroomRepository;

    @InjectMocks
    private SchoolClassFactoryImpl objectUnderTest;

    @Test
    void createClassroomTest(){
        ClassroomData classroomData = mock(ClassroomData.class);
        SchoolClass schoolClass = SchoolClass.builder().
                className(classroomData.className()).
                academicYear(classroomData.academicYear()).
                section(classroomData.section()).
                teacher(classroomData.teacher()).
                students(classroomData.students()).
                build();

        when(classroomRepository.existsByClassName(classroomData.className())).thenReturn(false);
        when(classroomRepository.save(schoolClass)).thenReturn(schoolClass);

        SchoolClass resultUnderTest = objectUnderTest.createClassroom(classroomData);

        assertThat(resultUnderTest)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(schoolClass);
    }

    @Test
    void getAllClassroomsTest(){

        SchoolClass schoolClass = mock(SchoolClass.class);
        SchoolClass schoolClass1 = mock(SchoolClass.class);
        SchoolClass schoolClass2 = mock(SchoolClass.class);

        when(classroomRepository.findAll()).thenReturn(List.of(schoolClass,schoolClass1 ,schoolClass2));

        List<SchoolClass> resultUnderTest= objectUnderTest.getAllClassrooms();

        assertThat(resultUnderTest).
                isNotEmpty().
                hasSize(3).
                startsWith(schoolClass).
                endsWith(schoolClass2);
    }

    @Test
    void getClassroomByIdTest(){
        long classRoomId = 1L;
        SchoolClass schoolClass = mock(SchoolClass.class);

        when(classroomRepository.findById(classRoomId)).thenReturn(Optional.of(schoolClass));

        SchoolClass resultUnderTest = objectUnderTest.getClassroomById(classRoomId);

        assertThat(resultUnderTest).
                isNotNull().
                usingRecursiveComparison().
                isEqualTo(schoolClass);
    }

    @Test
    void getClassroomByName(){
        ClassNames className = ClassNames.CLASS_1;
        SchoolClass schoolClass = mock(SchoolClass.class);

        when(classroomRepository.findByClassName(className)).
                thenReturn(Optional.of(schoolClass));

        SchoolClass resultUnderTest = objectUnderTest.getClassroomByName(className);

        assertThat(resultUnderTest).
                isNotNull().
                usingRecursiveComparison().
                isEqualTo(schoolClass);
    }

    @Test
    void updateClassroomTest(){
        long classroomId = 1L;
        ClassroomData classroomData = mock(ClassroomData.class);
        SchoolClass schoolClass = SchoolClass.builder().
                className(classroomData.className()).
                academicYear(classroomData.academicYear()).
                section(classroomData.section()).
                teacher(classroomData.teacher()).
                students(classroomData.students()).
                build();

        when(classroomRepository.existsById(classroomId)).thenReturn(true);
        when(classroomRepository.save(schoolClass)).thenReturn(schoolClass);

        objectUnderTest.updateClassroom(classroomId, classroomData);

        verify(classroomRepository, times(1)).existsById(classroomId);
        verify(classroomRepository,times(1)).save(schoolClass);
    }

    @Test
    void deleteClassroomTest(){

        long classroomId = 1L;

        doNothing().when(classroomRepository).deleteById(classroomId);

        objectUnderTest.deleteClassroom(classroomId);

        verify(classroomRepository, times(1)).deleteById(classroomId);
    }
}