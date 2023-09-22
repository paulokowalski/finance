package com.kowalski.finance.api.v1.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroResponse {

    String codigo;
    String descricao;

    public FiltroResponse(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}