package com.clustereddatawarehouse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "fx_deals")
@Getter
@Setter
@RequiredArgsConstructor
public class FxDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_id", unique = true, nullable = false)
    private UUID uniqueID;

    @Column(name = "from_currency", nullable = false)
    private Currency fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private Currency toCurrency;

    @Column(name = "deal_timestamp", nullable = false)
    private Instant dealTimestamp;

    @Column( name = "deal_amount", nullable = false)
    private BigDecimal dealAmount;

}
