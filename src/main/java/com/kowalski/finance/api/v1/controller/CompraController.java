package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.input.CompraInput;
import com.kowalski.finance.api.v1.response.CompraResponse;
import com.kowalski.finance.domain.model.Compra;
import com.kowalski.finance.domain.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/compra", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompraController {

    private final CompraService compraService;

    @GetMapping
    public List<Compra> buscarTodos(){
        return compraService.buscarTodos();
    }

    @GetMapping("/{ano}/{mes}/{pessoa}/{ultimaParcelaSelecionado}")
    public CompraResponse buscarPorMesENome(@PathVariable String ano, @PathVariable String mes, @PathVariable String pessoa, @PathVariable String ultimaParcelaSelecionado){
        return compraService.buscarPorMesENome(ano, mes, pessoa, ultimaParcelaSelecionado);
    }

    @PostMapping
    public Compra salvar(@Validated @RequestBody CompraInput compraInput) {
        return compraService.salvar(compraInput);
    }
}