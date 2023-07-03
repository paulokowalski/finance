package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.model.PurchaseModel;
import com.kowalski.finance.api.v1.response.PurchaseResponse;
import com.kowalski.finance.domain.model.Purchase;
import com.kowalski.finance.domain.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public List<Purchase> findAll(){
        return purchaseService.findAll();
    }

    @GetMapping("/{mounth}")
    public List<PurchaseResponse> findAllByMounth(@PathVariable String mounth){
        return purchaseService.findAllByMounth(mounth);
    }

    @PostMapping
    public Purchase save(@RequestBody PurchaseModel purchaseModel) {
        return purchaseService.save(purchaseModel);
    }
}