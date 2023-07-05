package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.InstallmentExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstallmentExpenseRespository extends JpaRepository<InstallmentExpense, UUID> {
}
