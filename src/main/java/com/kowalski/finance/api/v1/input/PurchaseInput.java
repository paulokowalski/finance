package com.kowalski.finance.api.v1.input;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseInput(
        String nameProduct,
        BigDecimal valueProduct,
        LocalDate datePurchase,
        Integer numberInstallment,
        String namePersonPurchase,
        String nameCard) {
}