package com.kowalski.finance.api.v1.response;

import com.kowalski.finance.domain.model.DespesaParcela;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
public class DespesaResponse {

    private UUID uuid;
    private String nameProduct;
    private String typeProduct;
    private BigDecimal valueProduct;
    private int installment;
    private int totalInstallment;

    public static DespesaResponse to(DespesaParcela despesaParcela) {
        var response = new DespesaResponse();
        response.setUuid(despesaParcela.getUuid());
        response.setNameProduct(despesaParcela.getDespesa().getNameProduct());
        response.setTypeProduct(despesaParcela.getDespesa().getTypeProduct());
        response.setValueProduct(despesaParcela.getValorParcela());
        response.setInstallment(despesaParcela.getNumeroParcela());
        response.setTotalInstallment(despesaParcela.getDespesa().getNumberInstallment());
        return response;
    }
}
