package com.clustereddatawarehouse.controller;

import com.clustereddatawarehouse.service.FxDealService;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/deals")
public class FxDealController {

    private FxDealService fxDealService;

    @PostMapping("/create")
    public ResponseEntity<FxDealDTO> newFxDeal(@RequestBody FxDealDTO fxDealDTO){
        FxDealDTO newFxDeal = fxDealService.save(fxDealDTO);
        return ResponseEntity.ok().
                body(newFxDeal);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FxDealDTO> getFxDealByUniqueId( @PathVariable("id") UUID uniqueId) {
        log.debug("REST request to get fxDeal by : {}", uniqueId);
        FxDealDTO fxDealDTO = fxDealService.findByUniqueId(uniqueId.toString()).orElseThrow();
        return ResponseEntity.ok(fxDealDTO);
    }

    @GetMapping("/fx-deals")
    public ResponseEntity<Page<FxDealDTO>> getAllFxDeals(@PageableDefault(value = 100)Pageable pageable) {
        log.debug("REST request to get list of fxDeals");
        return ResponseEntity.ok().body(fxDealService.findAll(pageable));
    }
}
