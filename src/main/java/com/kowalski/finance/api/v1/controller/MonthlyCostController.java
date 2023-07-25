package com.kowalski.finance.api.v1.controller;

import com.kowalski.finance.api.v1.response.CostResponse;
import com.kowalski.finance.domain.service.MonthlyCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/monthly-cost", produces = MediaType.APPLICATION_JSON_VALUE)
public class MonthlyCostController {

    private final MonthlyCostService monthlyCostService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CostResponse>> findCostByDateNow(){
        return ResponseEntity.ok(monthlyCostService.findCostByDateNow());
    }

    @PostMapping(path = "/updatemessageid/{uuid}/{messageId}")
    public void updateMessageId(@PathVariable UUID uuid, @PathVariable Integer messageId){
        monthlyCostService.updateMessageId(uuid, messageId);
    }

    @PostMapping(path = "/updatecost/{messageId}")
    public void updateCost(@PathVariable Integer messageId){
        monthlyCostService.updateCost(messageId);
    }
}
