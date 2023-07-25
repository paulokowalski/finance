package com.kowalski.finance.api.v1.response;

import com.kowalski.finance.domain.model.MonthlyCost;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
public class CostResponse {

    private UUID uuid;
    private String nameCost;
    private BigDecimal valueCost;

    public static CostResponse to(MonthlyCost monthlyCost) {
        var response = new CostResponse();
        response.setUuid(monthlyCost.getUuid());
        response.setNameCost(monthlyCost.getNameCost());
        response.setValueCost(monthlyCost.getValueCost());
        return response;
    }
}
