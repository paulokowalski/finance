package com.kowalski.finance.api.v1.input;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseInput(
        String nameProduct,
        String typeProduct,
        BigDecimal valueProduct,
        LocalDate dateExpense,
        Integer numberInstallment
) {
}