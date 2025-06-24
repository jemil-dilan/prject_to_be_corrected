package com.backend.studentRecordSystem.domain.parent;

import com.backend.studentRecordSystem.dto.ParentData;

import java.util.List;

public interface ParentFactory {
    Parent createParent(ParentData parentData);
    List<Parent> getAllParents();
    Parent getParentById(Long id);
    void updateParent(Long id, ParentData parentData);
    void deleteParent(Long id);

    void clearDatabase();
}
