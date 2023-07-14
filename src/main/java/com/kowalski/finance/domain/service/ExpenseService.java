package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.ExpenseInput;
import com.kowalski.finance.api.v1.response.ExpenseResponse;
import com.kowalski.finance.domain.model.Expense;
import com.kowalski.finance.domain.model.InstallmentExpense;
import com.kowalski.finance.domain.repository.ExpenseRepository;
import com.kowalski.finance.domain.repository.InstallmentExpenseRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final InstallmentExpenseRespository installmentExpenseRespository;

    @Transactional
    public Expense save(ExpenseInput expenseInput) {
        var expense = expenseRepository.save(Expense.builder()
                .nameProduct(expenseInput.nameProduct())
                .typeProduct(expenseInput.typeProduct())
                .valueProduct(expenseInput.valueProduct())
                .dateExpense(expenseInput.dateExpense())
                .numberInstallment(expenseInput.numberInstallment())
                .build());

        LocalDate dtInstallment = expense.getDateExpense();
        for(int x = 0; x < expenseInput.numberInstallment(); x++){
            var big = BigDecimal.valueOf(expenseInput.numberInstallment());

            installmentExpenseRespository.save(InstallmentExpense.builder()
                    .expense(expense)
                    .dateInstallment(dtInstallment.plusMonths(x+1))
                    .numberInstallment(x+1)
                    .valueInstallment(expenseInput.valueProduct().divide(big, 2, RoundingMode.CEILING))
                    .build());
        }
        return expense;
    }

    public List<ExpenseResponse> findExpenseByDateNow() {
        return installmentExpenseRespository.findExpenseByDateNow(LocalDate.now()).stream().map(ExpenseResponse::to).toList();
    }

    @Transactional
    public void updateMessageId(UUID uuid, Integer messageId){
        installmentExpenseRespository.updateMessageId(uuid, messageId);
    }

    @Transactional
    public void updateInstallmentExpense(Integer messageId) {
        installmentExpenseRespository.updateInstallmentExpense(true, messageId);
    }
}