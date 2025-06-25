package com.backend.studentRecordSystem.service.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.dto.classroom.CreateSchoolClassDTO;
import com.backend.studentRecordSystem.dto.classroom.SchoolClassSummaryDTO;
import com.backend.studentRecordSystem.domain.classroom.SchoolClassFactory;
import com.backend.studentRecordSystem.mapper.SchoolClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolClassServicesImpl implements SchoolClassServices {
    private final SchoolClassFactory classroomFactory;
    private final SchoolClassMapper classroomMapper;

    @Override
    public SchoolClassSummaryDTO createClassroom(CreateSchoolClassDTO createSchoolClassDTO) {
        return classroomMapper.toDTO(
                classroomFactory.createClassroom(
                        classroomMapper.toData(
                                createSchoolClassDTO
                        )
                )
        );
    }

    @Override
    public List<SchoolClassSummaryDTO> getAllClassrooms() {
        return classroomFactory.getAllClassrooms().stream().map(classroomMapper::toDTO).toList();
    }

    @Override
    public SchoolClassSummaryDTO getClassroomById(long classRoomId) {
        return classroomMapper.toDTO(classroomFactory.getClassroomById(classRoomId));
    }

    @Override
    public SchoolClassSummaryDTO getClassroomByName(ClassNames className) {
        return classroomMapper.toDTO(classroomFactory.getClassroomByName(className));
    }

    @Override
    public void updateClassroom(long classroomId, CreateSchoolClassDTO createSchoolClassDTO) {
        classroomFactory.updateClassroom(
                classroomId,
                classroomMapper.toData(createSchoolClassDTO)
        );
    }

    @Override
    public void deleteClassroom(long classroomId) {
        classroomFactory.deleteClassroom(classroomId);
    }
}
