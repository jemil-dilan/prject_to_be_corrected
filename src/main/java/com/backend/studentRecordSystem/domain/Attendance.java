package com.backend.studentRecordSystem.domain;

import com.backend.studentRecordSystem.domain.enums.Presence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_attendance_id", nullable = false)
    private Long id;
    @Column(name = "c_date", nullable = false)
    private LocalDate date;
    @Column(name = "c_status", nullable = false)
    private Presence status;
    @OneToOne
    @JoinColumn(name = "c_student_id", nullable = false)
    private Student student;
}
