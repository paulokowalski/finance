package com.kowalski.finance.domain.service;

import com.kowalski.finance.api.v1.response.FiltroResponse;
import com.kowalski.finance.domain.dao.FiltroDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FiltroService {

    private @Autowired FiltroDao filtroDao;

    public List<FiltroResponse> buscarAnos() {
        return filtroDao.buscarAnos();
    }

    public List<FiltroResponse> buscarMeses(String ano) {
        return filtroDao.buscarMeses(ano);
    }

    public List<FiltroResponse> buscarPessoas(String ano, String mes) {
        return filtroDao.buscarPessoas(ano, mes);
    }
}