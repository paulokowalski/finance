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
@Table(name = "compra_parcela")
public class CompraParcela {

    @Id
    @Column(name = "compra_parcela_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

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
    public CompraParcela(UUID uuid, Compra compra, Integer numeroParcela, BigDecimal valorParcela, LocalDate dataParcela) {
        this.uuid = uuid;
        this.compra = compra;
        this.numeroParcela = numeroParcela;
        this.valorParcela = valorParcela;
        this.dataParcela = dataParcela;
    }
}
