package com.clustereddatawarehouse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "fx_deals")
@Getter
@Setter
@RequiredArgsConstructor
public class FxDeals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "unique_id", unique = true, nullable = false)
    private long uniqueID;

    @Column(name = "from_currency", nullable = false)
    private Currency fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private Currency toCurrency;

    @Column(name = "deal_timestamp", nullable = false)
    private Instant dealTimestamp;

    @Column( name = "deal_amount", nullable = false)
    private BigDecimal dealAmount;

}
