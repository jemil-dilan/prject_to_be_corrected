package com.backend.studentRecordSystem.dto.parent;

import com.backend.studentRecordSystem.domain.enums.Relationship;
import com.backend.studentRecordSystem.domain.parent.Parent;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link Parent}
 */

@Builder
public record ParentDTO(Long id, String firstName, String lastName, String phoneNumber, String address,
                        Relationship relationship, String alternativeContact, String email,
                        String occupation) implements Serializable {
}