package com.clustereddatawarehouse.service.impl;

import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.service.FxDealService;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class FxDealServiceImpl implements FxDealService {
    @Override
    public FxDeal save(FxDealDTO fxDealDTO) {
        return null;
    }

    @Override
    public Page<FxDealDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<FxDeal> findByUniqueId(UUID uniqueId) {
        return Optional.empty();
    }
}
