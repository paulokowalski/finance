package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.CustoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustoMensalRepository extends JpaRepository<CustoMensal, UUID> {

    @Query(" SELECT mc FROM CustoMensal mc WHERE mc.dataCusto = :dataCusto AND mc.custoPago = false")
    List<CustoMensal> buscarCustoPorDataHoje(LocalDate dataCusto);


    @Modifying
    @Query(" UPDATE CustoMensal SET mensagemId = :mensagemId WHERE uuid = :uuid")
    void atualizarMensagemId(UUID uuid, Integer mensagemId);

    @Modifying
    @Query(" UPDATE CustoMensal SET custoPago = :custoPago WHERE mensagemId = :mensagemId")
    void atualizarCusto(boolean custoPago, Integer mensagemId);
}