package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.input.CustoInput;
import com.kowalski.finance.api.v1.response.CustoResponse;
import com.kowalski.finance.domain.model.CustoMensal;
import com.kowalski.finance.domain.service.CustoMensalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/custo-mensal", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustoMensalController {

    private final CustoMensalService custoMensalService;

    @PostMapping
    public CustoMensal salvar(@RequestBody CustoInput custoInput) {
        return custoMensalService.salvar(custoInput);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustoResponse>> buscarCustoPorDataHoje(){
        return ResponseEntity.ok(custoMensalService.buscarCustoPorDataHoje());
    }

    @PostMapping(path = "/atualizar-mensagem/{uuid}/{mensagemId}")
    public void atualizarMensagemId(@PathVariable UUID uuid, @PathVariable Integer mensagemId){
        custoMensalService.atualizarMensagemId(uuid, mensagemId);
    }

    @PostMapping(path = "/atualizar-custo/{mensagemId}")
    public void atualizarCusto(@PathVariable Integer mensagemId){
        custoMensalService.atualizarCusto(mensagemId);
    }
}
