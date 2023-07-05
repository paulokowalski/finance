package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.input.ExpenseInput;
import com.kowalski.finance.domain.model.Expense;
import com.kowalski.finance.domain.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/expense", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public Expense save(@RequestBody ExpenseInput expenseInput) {
        return expenseService.save(expenseInput);
    }
}