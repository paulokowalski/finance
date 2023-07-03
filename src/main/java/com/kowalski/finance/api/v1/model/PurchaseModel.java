package com.kowalski.finance.api.v1.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseModel(
        String nameProduct,
        BigDecimal valueProduct,
        LocalDate datePurchase,
        Integer numberInstallment,
        String namePersonPurchase,
        String nameCard) {
}