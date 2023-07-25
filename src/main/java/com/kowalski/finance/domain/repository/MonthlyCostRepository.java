package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.MonthlyCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MonthlyCostRepository extends JpaRepository<MonthlyCost, UUID> {

    @Query(" SELECT mc FROM MonthlyCost mc WHERE mc.datePayment = :localDate AND mc.costPaid = false")
    List<MonthlyCost> findCostByDateNow(LocalDate localDate);


    @Modifying
    @Query(" UPDATE MonthlyCost SET messageId = :messageId WHERE uuid = :uuid")
    void updateMessageId(UUID uuid, Integer messageId);

    @Modifying
    @Query(" UPDATE MonthlyCost SET costPaid = :costPaid WHERE messageId = :messageId")
    void updateCostExpense(boolean costPaid, Integer messageId);
}
