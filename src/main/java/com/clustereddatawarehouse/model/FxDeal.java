package com.clustereddatawarehouse.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "fx_deals")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FxDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_id", unique = true, nullable = false)
    private String uniqueId;

    @Column(name = "from_currency", nullable = false)
    private Currency fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private Currency toCurrency;

    @Column(name = "deal_timestamp", nullable = false)
    private Instant dealTimestamp;

    @Column( name = "deal_amount", nullable = false)
    private BigDecimal dealAmount;

}
