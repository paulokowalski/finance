package com.kowalski.finance.api.v1.input;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CustoInput(String nameCost, BigDecimal valueCost, LocalDate dateCost) {}
