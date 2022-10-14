package com.clustereddatawarehouse.service;

import com.clustereddatawarehouse.service.dto.FxDealDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = FxDealDTO.class)
public class FxDealDTOTest {

    @Test
    void testDTOConstructor(){
        FxDealDTO fxDealDTO = new FxDealDTO();
        fxDealDTO.setDealTimestamp(null);
        fxDealDTO.setFromCurrency("USD");
        fxDealDTO.setToCurrency("NGN");
        BigDecimal dealAmount = BigDecimal.valueOf(42L);
        fxDealDTO.setDealAmount(dealAmount);
        assertSame(dealAmount, fxDealDTO.getDealAmount());
        assertNull(fxDealDTO.getDealTimestamp());
        assertNotEquals("NGN", fxDealDTO.getFromCurrency());
        assertNotEquals("USD", fxDealDTO.getToCurrency());
        assertNotEquals(456L, fxDealDTO.getDealAmount().longValue());
    }
}
