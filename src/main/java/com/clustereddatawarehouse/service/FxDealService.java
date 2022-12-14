package com.clustereddatawarehouse.service;

import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface FxDealService {

    FxDealDTO save (FxDealDTO fxDealDTO);

    Page<FxDealDTO> findAll(Pageable pageable);

    Optional<FxDealDTO> findByUniqueId(String uniqueId);
}
