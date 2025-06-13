package com.backend.studentRecordSystem.service.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;
import com.backend.studentRecordSystem.dto.classroom.CreateSchoolClassDTO;
import com.backend.studentRecordSystem.dto.classroom.SchoolClassSummaryDTO;

import java.util.List;

public interface SchoolClassServices {
    SchoolClassSummaryDTO createClassroom(CreateSchoolClassDTO createSchoolClassDTO);

    List<SchoolClassSummaryDTO> getAllClassrooms();

    SchoolClassSummaryDTO getClassroomById(long classRoomId);

    SchoolClassSummaryDTO getClassroomByName(ClassNames className);

    void updateClassroom(long classroomId, CreateSchoolClassDTO createSchoolClassDTO);

    void deleteClassroom(long classroomId);
}
