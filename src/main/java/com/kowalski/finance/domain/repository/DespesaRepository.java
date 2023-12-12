package com.kowalski.finance.domain.repository;

import com.kowalski.finance.domain.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Optional<Despesa> findByNomePessoaAndMesAnoReferencia(String nomePessoa, String mesAnoReferencia);

}