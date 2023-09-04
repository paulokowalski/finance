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
public class Compra {

    @Id
    @Column(name = "compra_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "nm_produto")
    private String nomeProduto;

    @Column(name = "vl_produto")
    private BigDecimal valorProduto;

    @Column(name = "dt_compra")
    private LocalDate dataCompra;

    @Column(name = "nr_parcelas")
    private Integer numeroParcelas;

    @Column(name = "nm_pessoa_compra")
    private String nomePessoaCompra;

    @Column(name = "nm_cartao")
    private String nomeCartao;

}
