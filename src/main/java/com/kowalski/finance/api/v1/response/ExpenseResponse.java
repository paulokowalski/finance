package com.kowalski.finance.api.v1.response;

import com.kowalski.finance.domain.model.InstallmentExpense;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ExpenseResponse {

    public String nameProduct;
    public String typeProduct;
    public BigDecimal valueProduct;
    public int installment;
    public int totalInstallment;

    public static ExpenseResponse to(InstallmentExpense installmentExpense) {
        var response = new ExpenseResponse();
        response.setNameProduct(installmentExpense.getExpense().getNameProduct());
        response.setTypeProduct(installmentExpense.getExpense().getTypeProduct());
        response.setValueProduct(installmentExpense.getValueInstallment());
        response.setInstallment(installmentExpense.getNumberInstallment());
        response.setTotalInstallment(installmentExpense.getExpense().getNumberInstallment());
        return response;
    }
}
