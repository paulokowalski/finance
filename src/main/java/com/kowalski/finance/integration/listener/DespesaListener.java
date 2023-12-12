package com.kowalski.finance.integration.listener;

import com.kowalski.finance.config.kafka.event.CompraRealizadaEvent;
import com.kowalski.finance.domain.model.Despesa;
import com.kowalski.finance.domain.repository.CompraParcelaRepository;
import com.kowalski.finance.domain.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class DespesaListener {

    private final DespesaRepository repository;
    private final CompraParcelaRepository compraParcelaRepository;

    @KafkaListener(
            topics = "compra-realizada-topic",
            groupId = "compra-realizada-topic-id",
            containerFactory = "compraRealizadaEventDTOFactory")
    public void despesa(CompraRealizadaEvent compraRealizadaEvent){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/yyyy");
        var mesAnoReferencia = compraRealizadaEvent.getDataCompra().format(pattern);
        var despesa = repository.findByNomePessoaAndMesAnoReferencia(compraRealizadaEvent.getNomePessoa(), mesAnoReferencia);
        if(despesa.isEmpty()) {
            criarDespesa(compraRealizadaEvent.getNomePessoa(), mesAnoReferencia, compraRealizadaEvent.getNomeCartao());
        } else {
            atualizarDespesa(despesa.get(), compraRealizadaEvent.getNomePessoa(), mesAnoReferencia, compraRealizadaEvent.getNomeCartao());
        }
    }

    private void criarDespesa(String nomePessoa, String mesAnoReferencia, String cartao){
        var valorTotal = getValorTotalAnoMesPessoaCartao(nomePessoa, cartao, mesAnoReferencia);
        var despesa = Despesa
                .builder()
                .nomePessoa(nomePessoa)
                .mesAnoReferencia(mesAnoReferencia)
                .valorDespesaTotal(valorTotal)
                .tipoDespesa(cartao.toUpperCase())
                .build();
        repository.save(despesa);
    }

    private void atualizarDespesa(Despesa despesa, String nomePessoa, String mesAnoReferencia, String cartao){
        var valorTotal = getValorTotalAnoMesPessoaCartao(nomePessoa, cartao, mesAnoReferencia);
        despesa.setValorDespesaTotal(valorTotal);
        repository.save(despesa);
    }

    private BigDecimal getValorTotalAnoMesPessoaCartao(String nomePessoa, String cartao, String mesAnoReferencia) {
        var mes = mesAnoReferencia.split("/")[0];
        var ano = mesAnoReferencia.split("/")[1];
        var compras = compraParcelaRepository.buscarPorMesENomeEPessoaCartao(ano, mes, nomePessoa, cartao);
        return BigDecimal.valueOf(compras.stream().mapToDouble(compra -> compra.getValorParcela().doubleValue()).sum());
    }
}