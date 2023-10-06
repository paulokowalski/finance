package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.response.FiltroResponse;
import com.kowalski.finance.domain.service.FiltroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
public class FiltroController {

    private final FiltroService filtroService;

    @GetMapping("/anos")
    public ResponseEntity<List<FiltroResponse>> buscarAnos(){
        return ResponseEntity.of(Optional.ofNullable(filtroService.buscarAnos()));
    }

    @GetMapping("/meses/{ano}")
    public ResponseEntity<List<FiltroResponse>> buscarMeses(@PathVariable String ano){
        return ResponseEntity.of(Optional.ofNullable(filtroService.buscarMeses(ano)));
    }

    @GetMapping("/pessoas/{ano}/{mes}")
    public ResponseEntity<List<FiltroResponse>> buscarPessoas(@PathVariable String ano, @PathVariable String mes){
        return ResponseEntity.of(Optional.ofNullable(filtroService.buscarPessoas(ano, mes)));
    }
}
