package com.clustereddatawarehouse.service.validation;

import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.repository.FxDealRepository;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Currency;
import java.util.List;
import java.util.Objects;

@Service

public class FxDealValidation {

    private final FxDealRepository fxDealRepository;

    public FxDealValidation(FxDealRepository fxDealRepository){
        this.fxDealRepository = fxDealRepository;
    }

    public List<ErrorValidator> validateFxDeal(FxDealDTO fxDealDTO) {
        ErrorValidator errorValidator = new ErrorValidator();

        if (Objects.isNull(fxDealDTO)) {
            errorValidator.addError("error.fx.deal.null");
        } else {
            if (!StringUtils.hasText(fxDealDTO.getFromCurrency()) || !isISOCurrencyCodeValid(fxDealDTO.getFromCurrency()))
            {
                errorValidator.addError("error.fx.deal.currency.from.not.valid", fxDealDTO.getFromCurrency());
            }
            if (!StringUtils.hasText(fxDealDTO.getToCurrency()) || !isISOCurrencyCodeValid(fxDealDTO.getToCurrency()))
                {
                    errorValidator.addError("error.fx.deal.currency.to.not.valid", fxDealDTO.getFromCurrency());
                }
            if(fxDealDTO.getFromCurrency() != null && fxDealDTO.getToCurrency() == null && fxDealDTO.getToCurrency().equals(fxDealDTO.getFromCurrency())) {
                errorValidator.addError("error.fx.deal.currency.same", fxDealDTO.getToCurrency());
            }
            if(fxDealDTO.getDealAmount() == null) {
                errorValidator.addError("error.fx.deal.amount.not.valid");
            }
            if(fxDealDTO.getDealTimestamp() == null) {
                errorValidator.addError("error.fx.deal.timestamp.not.valid");
            }
            if(fxDealRepository.findByUniqueId(fxDealDTO.getUniqueID()).isPresent()){
                errorValidator.addError("error.fx.deal.unique.id.exist");
            }

        }
        return errorValidator.build();
    }

    private boolean isISOCurrencyCodeValid(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().anyMatch(c -> c.getCurrencyCode().equals(currencyCode));
    }
}
