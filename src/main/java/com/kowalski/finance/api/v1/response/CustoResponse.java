package com.kowalski.finance.api.v1.response;

import com.kowalski.finance.domain.model.CustoMensal;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
public class CustoResponse {

    private UUID uuid;
    private String nameCost;
    private BigDecimal valueCost;

    public static CustoResponse to(CustoMensal custoMensal) {
        var response = new CustoResponse();
        response.setUuid(custoMensal.getUuid());
        response.setNameCost(custoMensal.getNomeCusto());
        response.setValueCost(custoMensal.getValorCusto());
        return response;
    }
}
