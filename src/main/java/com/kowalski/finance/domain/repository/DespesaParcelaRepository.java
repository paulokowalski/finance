package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.DespesaParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DespesaParcelaRepository extends JpaRepository<DespesaParcela, UUID> {

    @Query(" SELECT ie FROM DespesaParcela ie WHERE ie.dataParcela = :localDate AND ie.despesaPaga = false")
    List<DespesaParcela> buscarDespesaDataHoje(LocalDate localDate);

    @Modifying
    @Query(" UPDATE DespesaParcela SET mensagemId = :mensagemId WHERE uuid = :uuid")
    void atualizarMensagem(Integer mensagemId, UUID uuid);

    @Modifying
    @Query(" UPDATE DespesaParcela SET despesaPaga = :despesaPaga WHERE mensagemId = :mensagemId")
    void atualizarDespesaParcela(boolean despesaPaga, Integer mensagemId);
}