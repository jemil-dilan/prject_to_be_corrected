package com.backend.studentRecordSystem.domain.parent;

import com.backend.studentRecordSystem.domain.student.Student;
import com.backend.studentRecordSystem.domain.enums.Relationship;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_parents")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_parent_id", nullable = false)
    private Long id;
    @Column(name = "c_parent_first_name", nullable = false)
    private String firstName;
    @Column(name = "c_parent_last_name", nullable = false)
    private String lastName;
    @Column(name = "c_phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "c_parent_address", nullable = false)
    private String address;
    @Column(name = "c_relationship_with_student", nullable = false)
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
    @Column(name = "c_alternative_contact")
    private String alternativeContact;
    @Column(name = "c_parent_email", unique = true, nullable = false)
    private String email;
    @Column(name= "c_parent_occupation", nullable = false)
    private String occupation;
    @ManyToMany(mappedBy = "parents")
    @Builder.Default
    private Set<Student> students = new HashSet<>();
}
