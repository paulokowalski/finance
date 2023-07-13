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
@Table(name = "installment_expense")
public class InstallmentExpense {

    @Id
    @Column(name = "installment_expense_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @Column(name = "nr_installment")
    private Integer numberInstallment;

    @Column(name = "vl_installment")
    private BigDecimal valueInstallment;

    @Column(name = "dt_installment")
    private LocalDate dateInstallment;

    public InstallmentExpense() {}

    public InstallmentExpense(UUID uuid, Expense expense, Integer numberInstallment, BigDecimal valueInstallment, LocalDate dateInstallment) {
        this.uuid = uuid;
        this.expense = expense;
        this.numberInstallment = numberInstallment;
        this.valueInstallment = valueInstallment;
        this.dateInstallment = dateInstallment;
    }
}
