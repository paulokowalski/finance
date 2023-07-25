package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.CostInput;
import com.kowalski.finance.api.v1.response.CostResponse;
import com.kowalski.finance.domain.model.MonthlyCost;
import com.kowalski.finance.domain.repository.MonthlyCostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonthlyCostService {

    private final MonthlyCostRepository monthlyCostRepository;

    @Transactional
    public MonthlyCost save(CostInput costInput) {
        return monthlyCostRepository.save(MonthlyCost.builder()
                .nameCost(costInput.nameCost())
                .valueCost(costInput.valueCost())
                .datePayment(costInput.dateCost())
                .build());
    }

    public List<CostResponse> findCostByDateNow() {
        return monthlyCostRepository.findCostByDateNow(LocalDate.now()).stream().map(CostResponse::to).toList();
    }

    @Transactional
    public void updateMessageId(UUID uuid, Integer messageId){
        monthlyCostRepository.updateMessageId(uuid, messageId);
    }

    @Transactional
    public void updateCost(Integer messageId) {
        monthlyCostRepository.updateCostExpense(true, messageId);
    }
}
