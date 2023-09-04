package com.kowalski.finance.api.v1.response;

import java.time.LocalDate;

public record CompraParcelaResponse(
        String nomeCompra,
        Double valorParcela,
        LocalDate dataParcela,
        int numeroTotalParcela,
        int numeroParcela,
        String ultimaParcela,
        String nomeCartao,
        Double valorFaltante,
        String nomePessoa,
        Double valorTotal) { }
