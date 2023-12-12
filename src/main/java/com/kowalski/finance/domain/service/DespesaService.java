package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.response.DespesaResponse;
import com.kowalski.finance.domain.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaResponse buscarPorAnoMesNome(String ano, String mes, String pessoa){
        var mesAnoReferencia = tratamentoMes(mes)+"/"+ano;
        var valorMes = buscarValorPorMesReferencia(pessoa, mesAnoReferencia);

        var mesAnoReferenciaProximoMes = tratamentoMes(String.valueOf(Integer.parseInt(mes)+ 1))  + "/" + ano;
        var valorProximoMes = buscarValorPorMesReferencia(pessoa, mesAnoReferenciaProximoMes);
        return new DespesaResponse(valorMes, valorProximoMes, valorMes.subtract(valorProximoMes));
    }

    private BigDecimal buscarValorPorMesReferencia(String pessoa, String mesAnoReferencia) {
        var valorMes = BigDecimal.ZERO;
        var despesa = repository.findByNomePessoaAndMesAnoReferencia(pessoa.toUpperCase(), mesAnoReferencia);
        if(despesa.isPresent()) {
            valorMes = despesa.get().getValorDespesaTotal();
        }
        return valorMes;
    }

    private String tratamentoMes(String mes){
        return mes.length() > 1 ? mes : "0" + mes;
    }
}