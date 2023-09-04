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
            " WHERE date_part('month', cp.dataParcela) = :mes " +
            " AND   date_part('year', cp.dataParcela) = 2023 " +
            " AND upper(cp.compra.nomePessoaCompra) = upper(:pessoa) " +
            " ORDER BY cp.compra.dataCompra DESC "
    )
    List<CompraParcela> buscarPorMesENome(Double mes, String pessoa);

}
