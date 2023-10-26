package com.kowalski.finance.domain.dao;

import com.kowalski.finance.api.v1.response.FiltroResponse;
import com.kowalski.finance.domain.enumerator.Meses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class FiltroDao {

    @Autowired
    private EntityManager entityManager;

    public List<FiltroResponse> buscarAnos(){
        String sql = " select distinct cast(tb01.ano as text), cast(tb01.ano as text) from ( " +
                     " select distinct DATE_PART('YEAR', dt_parcela) ano from compra_parcela cp " +
                     " ) tb01 order by cast(tb01.ano as text) desc ";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> rows = query.getResultList();
        List<FiltroResponse> responses = new ArrayList<>();
        for (Object[] row : rows) {
            responses.add(new FiltroResponse((String) row[0], (String) row[1]));
        }
        return responses;
    }

    public List<FiltroResponse> buscarMeses(String ano){
        String sql = " select distinct cast(tb01.mes as text), cast(tb01.mes as text) from (" +
                     " select distinct DATE_PART('MONTH', dt_parcela) mes from compra_parcela cp where cast(DATE_PART('YEAR', dt_parcela) as text) = :ano " +
                     " ) tb01 order by cast(tb01.mes as text) asc ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("ano", ano);
        List<Object[]> rows = query.getResultList();
        List<FiltroResponse> responses = new ArrayList<>();
        for (Object[] row : rows) {
            responses.add(new FiltroResponse((String) row[0], Arrays.asList(Meses.values()).get(Integer.parseInt((String) row[1])).toString()));
        }
        return responses;
    }

    public List<FiltroResponse> buscarPessoas(String ano, String mes){
        String sql = " select distinct c.nm_pessoa_compra, c.nm_pessoa_compra from compra c " +
                     " join compra_parcela cp on cp.compra_id = c.id " +
                     " where cast(DATE_PART('YEAR', dt_parcela) as text) = :ano " +
                     "   and cast(DATE_PART('MONTH', dt_parcela) as text) = :mes ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("ano", ano);
        query.setParameter("mes", mes);
        List<Object[]> rows = query.getResultList();
        List<FiltroResponse> responses = new ArrayList<>();
        for (Object[] row : rows) {
            responses.add(new FiltroResponse((String) row[0], (String) row[1]));
        }
        return responses;
    }
}