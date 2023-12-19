package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.CompraInput;
import com.kowalski.finance.api.v1.response.CompraResponse;
import com.kowalski.finance.config.kafka.event.CompraRealizadaEvent;
import com.kowalski.finance.domain.model.Compra;
import com.kowalski.finance.domain.model.CompraParcela;
import com.kowalski.finance.domain.repository.CompraParcelaRepository;
import com.kowalski.finance.domain.repository.CompraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;

    private final CompraParcelaRepository compraParcelaRepository;

    private final KafkaTemplate<String, Serializable> jsonKafkaTemplate;

    public List<Compra> buscarTodos(){
        return compraRepository.findAll();
    }

    public CompraResponse buscarPorMesENome(String ano, String mes, String pessoa, String ultimaParcelaSelecionado) {
        var lista = compraParcelaRepository.buscarPorMesENomeEPessoa(ano, mes, pessoa);
        return new CompraResponse(null).to(lista, ultimaParcelaSelecionado);
    }

    @Transactional
    public Compra salvar(CompraInput compraInput) {
        var compra = salvarCompra(compraInput);
        salvarParcelaEnviarEvento(compraInput, compra);
        return compra;
    }

    private Compra salvarCompra(CompraInput compraInput) {
        var compra = Compra.builder()
                .nomeProduto(compraInput.nomeProduto().toUpperCase())
                .nomeCartao(compraInput.nomeCartao().toUpperCase())
                .valorProduto(compraInput.valorProduto())
                .nomePessoaCompra(compraInput.nomePessoaCompra().toUpperCase())
                .dataCompra(compraInput.dataCompra())
                .numeroParcelas(compraInput.numeroParcelas())
                .build();
        compraRepository.save(compra);
        return compra;
    }

    private void salvarParcelaEnviarEvento(CompraInput compraInput, Compra compra) {
        LocalDate dtInstallment = compra.getDataCompra();
        for(int x = 0; x < compraInput.numeroParcelas(); x++){
            var big = BigDecimal.valueOf(compraInput.numeroParcelas());
            var compraParcela = CompraParcela.builder()
                    .compra(compra)
                    .dataParcela(dtInstallment.plusMonths(x+1L))
                    .numeroParcela(x+1)
                    .valorParcela(compraInput.valorProduto().divide(big, 2, RoundingMode.CEILING))
                    .build();
            compraParcelaRepository.save(compraParcela);
            jsonKafkaTemplate.send("compra-realizada-topic", new CompraRealizadaEvent().to(compraParcela));
        }
    }
}