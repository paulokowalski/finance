package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.InstallmentPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstallmentPurchaseRespository extends JpaRepository<InstallmentPurchase, UUID> {
}
