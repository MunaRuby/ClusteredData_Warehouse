package com.clustereddatawarehouse.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FxDeal.class)
public class FxDealTest {

    @Test
    void testConstructor() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealTimestamp(null);
        fxDeal.setFromCurrency(null);
        fxDeal.setToCurrency(null);
        BigDecimal dealAmount = BigDecimal.valueOf(765L);
        fxDeal.setDealAmount(dealAmount);
        assertSame(dealAmount, fxDeal.getDealAmount());
        assertNull(fxDeal.getDealTimestamp());
        assertNull(fxDeal.getFromCurrency());
        assertNull(fxDeal.getToCurrency());
        assertEquals(765L, fxDeal.getDealAmount().longValue());
    }
}
