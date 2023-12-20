package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.CompraParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompraParcelaRepository extends JpaRepository<CompraParcela, UUID> {

    @Query( " SELECT cp FROM CompraParcela cp " +
            " WHERE cast(date_part('month', cp.dataParcela) as text) = remove_zeros_esquerda(:mes) " +
            " AND   cast(date_part('year', cp.dataParcela) as text) = remove_zeros_esquerda(:ano) " +
            " AND upper(cp.compra.nomePessoaCompra) = upper(:pessoa) " +
            " ORDER BY cp.compra.dataCadastro DESC "
    )
    List<CompraParcela> buscarPorMesENomeEPessoa(String ano, String mes, String pessoa);

    @Query( " SELECT cp FROM CompraParcela cp " +
            " WHERE cast(date_part('month', cp.dataParcela) as text) = remove_zeros_esquerda(:mes) " +
            " AND   cast(date_part('year', cp.dataParcela) as text) = remove_zeros_esquerda(:ano) " +
            " AND upper(cp.compra.nomePessoaCompra) = upper(:pessoa) " +
            " AND upper(cp.compra.nomeCartao) like upper(:cartao) " +
            " ORDER BY cp.compra.dataCadastro DESC "
    )
    List<CompraParcela> buscarPorMesENomeEPessoaCartao(String ano, String mes, String pessoa, String cartao);

}