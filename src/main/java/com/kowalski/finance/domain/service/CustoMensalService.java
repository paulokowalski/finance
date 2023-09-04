package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.CustoInput;
import com.kowalski.finance.api.v1.response.CustoResponse;
import com.kowalski.finance.domain.model.CustoMensal;
import com.kowalski.finance.domain.repository.CustoMensalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustoMensalService {

    private final CustoMensalRepository custoMensalRepository;

    @Transactional
    public CustoMensal salvar(CustoInput custoInput) {
        return custoMensalRepository.save(CustoMensal.builder()
                .nomeCusto(custoInput.nameCost())
                .valorCusto(custoInput.valueCost())
                .dataCusto(custoInput.dateCost())
                .build());
    }

    public List<CustoResponse> buscarCustoPorDataHoje() {
        return custoMensalRepository.buscarCustoPorDataHoje(LocalDate.now()).stream().map(CustoResponse::to).toList();
    }

    @Transactional
    public void atualizarMensagemId(UUID uuid, Integer messageId){
        custoMensalRepository.atualizarMensagemId(uuid, messageId);
    }

    @Transactional
    public void atualizarCusto(Integer messageId) {
        custoMensalRepository.atualizarCusto(true, messageId);
    }
}
