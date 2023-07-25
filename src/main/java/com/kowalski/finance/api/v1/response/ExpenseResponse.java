package com.kowalski.finance.api.v1.response;

import com.kowalski.finance.domain.model.InstallmentExpense;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
public class ExpenseResponse {

    private UUID uuid;
    private String nameProduct;
    private String typeProduct;
    private BigDecimal valueProduct;
    private int installment;
    private int totalInstallment;

    public static ExpenseResponse to(InstallmentExpense installmentExpense) {
        var response = new ExpenseResponse();
        response.setUuid(installmentExpense.getUuid());
        response.setNameProduct(installmentExpense.getExpense().getNameProduct());
        response.setTypeProduct(installmentExpense.getExpense().getTypeProduct());
        response.setValueProduct(installmentExpense.getValueInstallment());
        response.setInstallment(installmentExpense.getNumberInstallment());
        response.setTotalInstallment(installmentExpense.getExpense().getNumberInstallment());
        return response;
    }
}
