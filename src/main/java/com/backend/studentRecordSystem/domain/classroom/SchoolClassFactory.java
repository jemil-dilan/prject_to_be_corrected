package com.backend.studentRecordSystem.domain.classroom;

import com.backend.studentRecordSystem.domain.enums.ClassNames;

import java.util.List;

public interface SchoolClassFactory {
    SchoolClass createClassroom(ClassroomData classroomData);

    List<SchoolClass> getAllClassrooms();

    SchoolClass getClassroomById(long classRoomId);

    SchoolClass getClassroomByName(ClassNames className);

    void updateClassroom(long classroomId, ClassroomData classroomData);

    void deleteClassroom(long classroomId);
}
