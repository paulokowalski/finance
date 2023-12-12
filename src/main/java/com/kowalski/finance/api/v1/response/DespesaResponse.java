package com.kowalski.finance.api.v1.response;

import java.math.BigDecimal;

public record DespesaResponse (

    BigDecimal valorMes,

    BigDecimal valorProximoMes,

    BigDecimal valorSaindo
)
{ }