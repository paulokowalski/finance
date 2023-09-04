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
@Table(name = "custo_mensal")
public class CustoMensal {

    @Id
    @Column(name = "custo_mensal_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nm_custo")
    private String nomeCusto;

    @Column(name = "vl_custo")
    private BigDecimal valorCusto;

    @Column(name = "dt_custo")
    private LocalDate dataCusto;

    @Column(name = "custo_pago")
    private boolean custoPago;

    @Column(name = "mensagem_id")
    private Integer mensagemId;
}
