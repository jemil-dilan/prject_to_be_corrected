package com.backend.studentRecordSystem.dto;

import com.backend.studentRecordSystem.domain.enums.Relationship;
import lombok.Builder;

@Builder
public record ParentData(
        Long id, String firstName, String lastName, String phoneNumber, String address,
        Relationship relationship, String alternativeContact, String email,
        String occupation
) {
}
