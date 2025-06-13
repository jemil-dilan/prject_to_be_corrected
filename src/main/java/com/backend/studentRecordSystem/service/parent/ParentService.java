package com.backend.studentRecordSystem.service.parent;

import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;

import java.util.List;


public interface ParentService {
    List<ParentDTO> getAllParents();
    ParentDTO getParentById(Long id);
    void deleteParent(Long id);
    ParentDTO createParent(CreateParentDTO createParentDTO);
    void updateParent(Long id, CreateParentDTO createParentDTO);

    void clearDatabase();
}
