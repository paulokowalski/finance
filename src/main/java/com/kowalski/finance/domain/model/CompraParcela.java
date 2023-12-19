package com.kowalski.finance.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@Table(name = "compra_parcela")
@SequenceGenerator(name = "sequencecompraparcela", sequenceName = "compra_parcela_id_seq", allocationSize = 1)
public class CompraParcela {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequencecompraparcela")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @Column(name = "nr_parcela")
    private Integer numeroParcela;

    @Column(name = "vl_parcela")
    private BigDecimal valorParcela;

    @Column(name = "dt_parcela")
    private LocalDate dataParcela;

    public CompraParcela() {}
    public CompraParcela(Long id, Compra compra, Integer numeroParcela, BigDecimal valorParcela, LocalDate dataParcela) {
        this.id = id;
        this.compra = compra;
        this.numeroParcela = numeroParcela;
        this.valorParcela = valorParcela;
        this.dataParcela = dataParcela;
    }

    public boolean isUltimaParcela() {
        return this.getCompra().getNumeroParcelas().equals(this.getNumeroParcela());
    }
}