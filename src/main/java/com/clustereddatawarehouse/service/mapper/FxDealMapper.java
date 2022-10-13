package com.clustereddatawarehouse.service.mapper;

import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.service.dto.FxDealDTO;

import java.util.List;

public interface FxDealMapper {
    FxDeal toEntity (FxDealDTO fxDealDTO);

    FxDealDTO fxDealDTO(FxDeal fxDeal);

    List<FxDeal> toEntity(List<FxDealDTO> dtoList);

    List<FxDealDTO> toDto(List<FxDeal> entityList);
}
