package com.clustereddatawarehouse.service.impl;

import com.clustereddatawarehouse.exception.ValidationException;
import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.repository.FxDealRepository;
import com.clustereddatawarehouse.service.FxDealService;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import com.clustereddatawarehouse.service.mapper.FxDealMapper;
import com.clustereddatawarehouse.service.validation.ErrorValidator;
import com.clustereddatawarehouse.service.validation.FxDealValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class FxDealServiceImpl implements FxDealService {
    private FxDealValidation fxDealValidation;
    private FxDealRepository fxDealRepository;
    private FxDealMapper fxDealMapper;

    @Override
    public FxDealDTO save(FxDealDTO fxDealDTO) {

        UUID uniqueId = UUID.randomUUID();
        fxDealDTO.setUniqueID(uniqueId);
        List<ErrorValidator> errorValidators = fxDealValidation.validateFxDeal(fxDealDTO);
        if(!CollectionUtils.isEmpty(errorValidators)) throw new ValidationException(errorValidators);

        FxDeal fxDeal = fxDealMapper.toEntity(fxDealDTO);
        fxDeal = fxDealRepository.save(fxDeal);

        return fxDealMapper.fxDealDTO(fxDeal);
    }

    @Override
    public Page<FxDealDTO> findAll(Pageable pageable) {
        return fxDealRepository.findAll(pageable).map(fxDealMapper::fxDealDTO);
    }

    @Override
    public Optional<FxDealDTO> findByUniqueId(UUID uniqueId) {
        return fxDealRepository.findByUniqueId(uniqueId).map(fxDealMapper::fxDealDTO);
    }
}
