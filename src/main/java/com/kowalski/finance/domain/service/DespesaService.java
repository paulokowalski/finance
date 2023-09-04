package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.DespesaInput;
import com.kowalski.finance.api.v1.response.DespesaResponse;
import com.kowalski.finance.domain.model.Despesa;
import com.kowalski.finance.domain.model.DespesaParcela;
import com.kowalski.finance.domain.repository.DespesaRepository;
import com.kowalski.finance.domain.repository.DespesaParcelaRepository;
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
public class DespesaService {

    private final DespesaRepository despesaRepository;

    private final DespesaParcelaRepository despesaParcelaRepository;

    @Transactional
    public Despesa salvar(DespesaInput despesaInput) {
        var expense = despesaRepository.save(Despesa.builder()
                .nameProduct(despesaInput.nameProduct())
                .typeProduct(despesaInput.typeProduct())
                .valueProduct(despesaInput.valueProduct())
                .dateExpense(despesaInput.dateExpense())
                .numberInstallment(despesaInput.numberInstallment())
                .build());

        LocalDate dtInstallment = expense.getDateExpense();
        for(int x = 0; x < despesaInput.numberInstallment(); x++){
            var big = BigDecimal.valueOf(despesaInput.numberInstallment());

            despesaParcelaRepository.save(DespesaParcela.builder()
                    .despesa(expense)
                    .dataParcela(dtInstallment.plusMonths(x+1L))
                    .numeroParcela(x+1)
                    .valorParcela(despesaInput.valueProduct().divide(big, 2, RoundingMode.CEILING))
                    .build());
        }
        return expense;
    }

    public List<DespesaResponse> buscarDespesaDataHoje() {
        return despesaParcelaRepository.buscarDespesaDataHoje(LocalDate.now()).stream().map(DespesaResponse::to).toList();
    }

    @Transactional
    public void atualizarMensagem(UUID uuid, Integer messageId){
        despesaParcelaRepository.atualizarMensagem(messageId, uuid);
    }

    @Transactional
    public void atualizarDespesaParcela(Integer messageId) {
        despesaParcelaRepository.atualizarDespesaParcela(true, messageId);
    }
}