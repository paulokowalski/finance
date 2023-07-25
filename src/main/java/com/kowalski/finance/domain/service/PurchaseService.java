package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.PurchaseInput;
import com.kowalski.finance.api.v1.response.PurchaseResponse;
import com.kowalski.finance.domain.model.InstallmentPurchase;
import com.kowalski.finance.domain.model.Purchase;
import com.kowalski.finance.domain.repository.InstallmentPurchaseRespository;
import com.kowalski.finance.domain.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final InstallmentPurchaseRespository installmentPurchaseRespository;

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public List<PurchaseResponse> findAllByMounth(String mounth) {
        return purchaseRepository.findAllByMounth(Double.valueOf(mounth));
    }

    @Transactional
    public Purchase save(PurchaseInput purchaseInput) {
        var purchase =  purchaseRepository.save(Purchase.builder()
                .nameProduct(purchaseInput.nameProduct())
                .nameCard(purchaseInput.nameCard())
                .valueProduct(purchaseInput.valueProduct())
                .namePersonPurchase(purchaseInput.namePersonPurchase())
                .datePurchase(purchaseInput.datePurchase())
                .numberInstallment(purchaseInput.numberInstallment())
                .build());

        LocalDate dtInstallment = purchase.getDatePurchase();
        for(int x = 0; x < purchaseInput.numberInstallment(); x++){
            var big = BigDecimal.valueOf(purchaseInput.numberInstallment());

            installmentPurchaseRespository.save(InstallmentPurchase.builder()
                    .purchase(purchase)
                    .dateInstallment(dtInstallment.plusMonths(x+1L))
                    .numberInstallment(x+1)
                    .valueInstallment(purchaseInput.valueProduct().divide(big, 2, RoundingMode.CEILING))
                    .build());
        }
        return purchase;
    }
}
