package com.kowalski.finance.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Expense {

    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nm_product")
    private String nameProduct;

    @Column(name = "tp_product")
    private String typeProduct;

    @Column(name = "vl_product")
    private BigDecimal valueProduct;

    @Column(name = "dt_expense")
    private LocalDate dateExpense;

    @Column(name = "nr_installment")
    private Integer numberInstallment;

}
