package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.input.ExpenseInput;
import com.kowalski.finance.api.v1.response.ExpenseResponse;
import com.kowalski.finance.domain.model.Expense;
import com.kowalski.finance.domain.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/expense", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public Expense save(@RequestBody ExpenseInput expenseInput) {
        return expenseService.save(expenseInput);
    }

    @PostMapping(path = "/updatemessageid/{uuid}/{messageId}")
    public void updateMessageId(@PathVariable UUID uuid, @PathVariable Integer messageId){
        expenseService.updateMessageId(uuid, messageId);
    }

    @PostMapping(path = "/updateinstallmentexpense/{messageId}")
    public void updateInstallmentExpense(@PathVariable Integer messageId){
        expenseService.updateInstallmentExpense(messageId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExpenseResponse>> findExpenseByDateNow(){
        return ResponseEntity.ok(expenseService.findExpenseByDateNow());
    }
}