package com.kowalski.finance.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@Table(name = "installment_purchase")
public class InstallmentPurchase {

    @Id
    @Column(name = "installment_purchase_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column(name = "nr_installment")
    private Integer numberInstallment;

    @Column(name = "vl_installment")
    private BigDecimal valueInstallment;

    @Column(name = "dt_installment")
    private LocalDate dateInstallment;
}
