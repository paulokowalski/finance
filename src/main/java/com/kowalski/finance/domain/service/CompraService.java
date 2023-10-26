package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.input.CompraInput;
import com.kowalski.finance.api.v1.response.CompraCartaoResponse;
import com.kowalski.finance.api.v1.response.CompraParcelaResponse;
import com.kowalski.finance.api.v1.response.CompraResponse;
import com.kowalski.finance.domain.model.Compra;
import com.kowalski.finance.domain.model.CompraParcela;
import com.kowalski.finance.domain.repository.CompraParcelaRepository;
import com.kowalski.finance.domain.repository.CompraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;

    private final CompraParcelaRepository compraParcelaRepository;

    public List<Compra> buscarTodos(){
        return compraRepository.findAll();
    }

    public CompraResponse buscarPorMesENome(String ano, String mes, String pessoa) {
        var lista = compraParcelaRepository.buscarPorMesENomeEPessoa(ano, mes, pessoa);
        var listaCartao = compraParcelaRepository.buscarPorMesENome(ano, mes);
        var valorTotal = somatorioDasParcelas(lista);
        var valorProximoMes = calcularValorProximoMes(lista);
        return new CompraResponse(valorTotal, valorProximoMes, getCompraCartao(listaCartao), getCompraParcela(lista));
    }

    private List<CompraCartaoResponse> getCompraCartao(List<CompraParcela> lista) {
        List<CompraCartaoResponse> response = new ArrayList<>();
        lista.stream()
                .collect(Collectors.groupingBy(parcela -> parcela.getCompra().getNomeCartao()))
                .forEach((cartao, parcelas) -> response.add(new CompraCartaoResponse(cartao, somatorioDasParcelas(parcelas))) );
        return response;
    }

    private static double somatorioDasParcelas(List<CompraParcela> parcelas) {
        return parcelas.stream().mapToDouble(parcela -> parcela.getValorParcela().doubleValue()).sum();
    }

    private List<CompraParcelaResponse> getCompraParcela(List<CompraParcela> lista) {
        return lista.stream().map(cp ->
                new CompraParcelaResponse(
                        cp.getCompra().getNomeProduto().toUpperCase(),
                        cp.getValorParcela().doubleValue(),
                        cp.getDataParcela(),
                        cp.getCompra().getNumeroParcelas(),
                        cp.getNumeroParcela(),
                        ultimaParcela(cp),
                        cp.getCompra().getNomeCartao().toUpperCase(),
                        calculaValorFalta(cp),
                        cp.getCompra().getNomePessoaCompra().toUpperCase(),
                        cp.getCompra().getValorProduto().doubleValue()))
                .toList();
    }

    private String ultimaParcela(CompraParcela cp){
        return cp.getCompra().getNumeroParcelas().equals(cp.getNumeroParcela()) ? "Sim" : "Não";
    }

    private Double calculaValorFalta(CompraParcela cp) {
        if(cp.getCompra().getNumeroParcelas().equals(cp.getNumeroParcela())) return Double.valueOf("0");
        var numeroParcelas = cp.getCompra().getNumeroParcelas() - cp.getNumeroParcela();
        return cp.getValorParcela().doubleValue() * numeroParcelas;
    }

    private Double calcularValorProximoMes(List<CompraParcela> compras) {
        return compras.stream()
                .filter(cp -> cp.getCompra().getNumeroParcelas() - cp.getNumeroParcela() > 0)
                .mapToDouble(cp -> cp.getValorParcela().doubleValue()).sum();
    }

    @Transactional
    public Compra salvar(CompraInput compraInput) {
        var purchase =  compraRepository.save(Compra.builder()
                .nomeProduto(compraInput.nomeProduto().toUpperCase())
                .nomeCartao(compraInput.nomeCartao().toUpperCase())
                .valorProduto(compraInput.valorProduto())
                .nomePessoaCompra(compraInput.nomePessoaCompra().toUpperCase())
                .dataCompra(compraInput.dataCompra().plusDays(1))
                .numeroParcelas(compraInput.numeroParcelas())
                .build());

        LocalDate dtInstallment = purchase.getDataCompra();
        for(int x = 0; x < compraInput.numeroParcelas(); x++){
            var big = BigDecimal.valueOf(compraInput.numeroParcelas());

            compraParcelaRepository.save(CompraParcela.builder()
                    .compra(purchase)
                    .dataParcela(dtInstallment.plusMonths(x+1L))
                    .numeroParcela(x+1)
                    .valorParcela(compraInput.valorProduto().divide(big, 2, RoundingMode.CEILING))
                    .build());
        }
        return purchase;
    }
}
