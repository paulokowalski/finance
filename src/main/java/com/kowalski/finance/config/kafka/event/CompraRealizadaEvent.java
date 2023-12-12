package com.kowalski.finance.config.kafka.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.kowalski.finance.domain.model.CompraParcela;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompraRealizadaEvent implements Serializable {

    String nomeCompra;

    String nomeCartao;

    BigDecimal valorProduto;

    String nomePessoa;

    int numeroParcela;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate dataCompra;

    public CompraRealizadaEvent to(CompraParcela compraParcela){
        var compraRealizadaEvent = new CompraRealizadaEvent();
        compraRealizadaEvent.setNomeCompra(compraParcela.getCompra().getNomeProduto());
        compraRealizadaEvent.setNomeCartao(compraParcela.getCompra().getNomeCartao());
        compraRealizadaEvent.setNomePessoa(compraParcela.getCompra().getNomePessoaCompra());
        compraRealizadaEvent.setDataCompra(compraParcela.getDataParcela());
        compraRealizadaEvent.setValorProduto(compraParcela.getValorParcela());
        compraRealizadaEvent.setNumeroParcela(compraParcela.getNumeroParcela());
        return compraRealizadaEvent;
    }
}
