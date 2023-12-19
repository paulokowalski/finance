package com.kowalski.finance.api.v1.response;

import com.kowalski.finance.domain.model.CompraParcela;
import io.micrometer.common.util.StringUtils;

import java.util.List;

public record CompraResponse (
        List<CompraParcelaResponse> compras
)
{
    public CompraResponse to(List<CompraParcela> compras, String ultimaParcelaSelecionado){
        var compraParcelaResponse = compras.stream().map(this::newCompraParcelaResponse).toList();
        if(StringUtils.isNotBlank(ultimaParcelaSelecionado) && !ultimaParcelaSelecionado.equalsIgnoreCase("SELECIONE")){
            compraParcelaResponse = compraParcelaResponse.stream().filter(compra -> compra.ultimaParcela().equalsIgnoreCase(ultimaParcelaSelecionado)).toList();
        }
        return new CompraResponse(compraParcelaResponse);
    }

    private CompraParcelaResponse newCompraParcelaResponse(CompraParcela cp) {
        return new CompraParcelaResponse(
                cp.getCompra().getNomeProduto().toUpperCase(),
                cp.getValorParcela().doubleValue(),
                cp.getDataParcela(),
                cp.getCompra().getNumeroParcelas(),
                cp.getNumeroParcela(),
                cp.isUltimaParcela() ? "SIM" : "NÃO",
                cp.getCompra().getNomeCartao().toUpperCase(),
                calculaValorFalta(cp),
                cp.getCompra().getNomePessoaCompra().toUpperCase(),
                cp.getCompra().getValorProduto().doubleValue());
    }

    private Double calculaValorFalta(CompraParcela cp) {
        if(cp.getCompra().getNumeroParcelas().equals(cp.getNumeroParcela())) return Double.valueOf("0");
        var numeroParcelas = cp.getCompra().getNumeroParcelas() - cp.getNumeroParcela();
        return cp.getValorParcela().doubleValue() * numeroParcelas;
    }
}