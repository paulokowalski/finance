package com.kowalski.finance.api.v1.input;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CostInput(String nameCost, BigDecimal valueCost, LocalDate dateCost) {}
