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
public class Despesa {

    @Id
    @Column(name = "despesa_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nm_despesa")
    private String nameProduct;

    @Column(name = "tp_despesa")
    private String typeProduct;

    @Column(name = "vl_despesa")
    private BigDecimal valueProduct;

    @Column(name = "dt_despesa")
    private LocalDate dateExpense;

    @Column(name = "nr_parcelas_despesa")
    private Integer numberInstallment;

}
