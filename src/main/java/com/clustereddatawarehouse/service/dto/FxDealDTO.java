package com.clustereddatawarehouse.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Getter
@Setter
public class FxDealDTO{

    private UUID uniqueId;

    private String fromCurrency;

    private String toCurrency;

    private Instant dealTimestamp;

    private BigDecimal dealAmount;


}
