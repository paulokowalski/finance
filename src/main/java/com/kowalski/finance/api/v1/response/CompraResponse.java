package com.kowalski.finance.api.v1.response;

import java.util.List;

public record CompraResponse (

        Double valorTotal,
        Double valorProximoMes,
        List<CompraCartaoResponse> comprasCartao,
        List<CompraParcelaResponse> compras

) { }
