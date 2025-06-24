package com.backend.studentRecordSystem.mapper;

import com.backend.studentRecordSystem.domain.parent.Parent;
import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.dto.ParentData;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    ParentDTO toDto(Parent parent);

    @Mapping(target = "id", ignore = true)
    ParentData toData(CreateParentDTO createParentDTO);
}
