package com.kowalski.finance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "monthly_cost")
public class MonthlyCost {

    @Id
    @Column(name = "monthly_cost_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nm_cost")
    private String nameCost;

    @Column(name = "vl_cost")
    private BigDecimal valueCost;

    @Column(name = "dt_payment")
    private LocalDate datePayment;

    @Column(name = "cost_paid")
    private boolean costPaid;

    private Integer messageId;
}
