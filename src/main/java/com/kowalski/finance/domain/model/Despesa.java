package com.kowalski.finance.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Despesa implements Serializable {

    @Id
    @Column(name = "id_despesa")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idDespesa;

    @Column(name = "nm_pessoa")
    private String nomePessoa;

    @Column(name = "nr_mes_ano_referencia")
    private String mesAnoReferencia;

    @Column(name = "nm_tipo_despesa")
    private String tipoDespesa;

    @Column(name = "vl_despesa_total")
    private BigDecimal valorDespesaTotal;

}