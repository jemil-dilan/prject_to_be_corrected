package com.backend.studentRecordSystem.domain;

import com.backend.studentRecordSystem.domain.staff.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_fees_&_payments")
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_payment_id", nullable = false )
    private Long id;
    @Column(name = "c_amount_paid", nullable = false)
    private BigDecimal amount;
    @Column(name = "c_paymentDate", nullable = false)
    @Builder.Default
    private LocalDateTime paymentDate = LocalDateTime.now();
    @OneToOne
    @JoinColumn(name = "c_staff_id", nullable = false)
    private Staff staff;
}