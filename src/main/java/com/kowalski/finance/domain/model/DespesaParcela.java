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
@Table(name = "despesa_parcela")
public class DespesaParcela {

    @Id
    @Column(name = "despesa_parcela_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "despesa_id")
    private Despesa despesa;

    @Column(name = "nr_parcela_despesa")
    private Integer numeroParcela;

    @Column(name = "vl_despesa")
    private BigDecimal valorParcela;

    @Column(name = "dt_despesa")
    private LocalDate dataParcela;

    @Column(name = "despesa_paga")
    private boolean despesaPaga;

    @Column(name = "mensagem_id")
    private Integer mensagemId;

    public DespesaParcela() {}

    public DespesaParcela(UUID uuid, Despesa despesa, Integer numeroParcela, BigDecimal valorParcela, LocalDate dataParcela, boolean despesaPaga, Integer mensagemId) {
        this.uuid = uuid;
        this.despesa = despesa;
        this.numeroParcela = numeroParcela;
        this.valorParcela = valorParcela;
        this.dataParcela = dataParcela;
        this.despesaPaga = despesaPaga;
        this.mensagemId = mensagemId;
    }
}
