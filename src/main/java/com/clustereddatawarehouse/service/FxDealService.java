package com.clustereddatawarehouse.service;

import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface FxDealService {

    FxDeal save (FxDealDTO fxDealDTO);

    Page<FxDealDTO> findAll(Pageable pageable);

    Optional<FxDeal> findByUniqueId(UUID uniqueId);
}
