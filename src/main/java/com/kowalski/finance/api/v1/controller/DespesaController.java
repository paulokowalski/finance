package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.input.DespesaInput;
import com.kowalski.finance.api.v1.response.DespesaResponse;
import com.kowalski.finance.domain.model.Despesa;
import com.kowalski.finance.domain.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/despesa", produces = MediaType.APPLICATION_JSON_VALUE)
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping
    public Despesa salvar(@RequestBody DespesaInput despesaInput) {
        return despesaService.salvar(despesaInput);
    }

    @PostMapping(path = "/atualizar-mensagem/{uuid}/{mensagemId}")
    public void atualizarMensagem(@PathVariable UUID uuid, @PathVariable Integer mensagemId){
        despesaService.atualizarMensagem(uuid, mensagemId);
    }

    @PostMapping(path = "/atualizar-despesa-parcela/{mensagemId}")
    public void atualizarDespesaParcela(@PathVariable Integer mensagemId){
        despesaService.atualizarDespesaParcela(mensagemId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DespesaResponse>> buscarDespesaDataHoje(){
        return ResponseEntity.ok(despesaService.buscarDespesaDataHoje());
    }
}