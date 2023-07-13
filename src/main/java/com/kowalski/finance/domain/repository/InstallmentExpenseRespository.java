package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.InstallmentExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface InstallmentExpenseRespository extends JpaRepository<InstallmentExpense, UUID> {

    @Query(" SELECT ie FROM InstallmentExpense ie WHERE ie.dateInstallment = :localDate ")
    List<InstallmentExpense> findExpenseByDateNow(LocalDate localDate);

}