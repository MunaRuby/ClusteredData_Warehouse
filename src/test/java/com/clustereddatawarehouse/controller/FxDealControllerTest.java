package com.clustereddatawarehouse.controller;

import com.clustereddatawarehouse.ClusteredDataWarehouseApplication;
import com.clustereddatawarehouse.model.FxDeal;
import com.clustereddatawarehouse.repository.FxDealRepository;
import com.clustereddatawarehouse.service.FxDealService;
import com.clustereddatawarehouse.service.dto.FxDealDTO;
import com.clustereddatawarehouse.service.mapper.FxDealMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = ClusteredDataWarehouseApplication.class)
public class FxDealControllerTest {

    @Autowired
    FxDealMapper fxDealMapper;

    @Autowired
    FxDealService fxDealService;

    @Autowired
    FxDealRepository fxDealRepository;

    @Autowired
    private MockMvc restFxDealMockMvc;

    private FxDeal fxDeal;

    private final static BigDecimal DEFAULT_DEAL_AMOUNT = BigDecimal.ONE;
    private final static Currency DEFAULT_FROM_CURRENCY = Currency.getInstance("USD");
    private final static Currency DEFAULT_TO_CURRENCY = Currency.getInstance("NGN");
    private final static Instant DEFAULT_DEAL_TIMESTAMP = Instant.now();

    private static final ObjectMapper mapper = createObjectMapper();

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @BeforeEach
    public void initTest() {
        fxDeal = createDeal();
    }

    @AfterEach
    public void destroyTest() {
        fxDealRepository.deleteAll();
    }

    public static FxDeal createDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealAmount(DEFAULT_DEAL_AMOUNT);
        fxDeal.setFromCurrency(DEFAULT_FROM_CURRENCY);
        fxDeal.setToCurrency(DEFAULT_TO_CURRENCY);
        fxDeal.setId(1L);
        fxDeal.setDealTimestamp(DEFAULT_DEAL_TIMESTAMP);
        return fxDeal;
    }

    @Transactional
    @Test
    void createFxDeal() throws Exception {
        int databaseSizeBeforeCreate = fxDealRepository.findAll().size();
        // Create the FxDeal
        FxDealDTO fxDealDTO = fxDealMapper.fxDealDTO(fxDeal);
        restFxDealMockMvc
                .perform(post("/deals/create").contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(fxDealDTO)))
                .andExpect(status().isOk());

        // Validate the FxDeal in the database
        List<FxDeal> fxDealList = fxDealRepository.findAll();
        assertThat(fxDealList).hasSize(databaseSizeBeforeCreate + 1);
        FxDeal testFxDeal = fxDealList.get(fxDealList.size() - 1);
        assertThat(testFxDeal.getDealAmount()).isEqualTo(DEFAULT_DEAL_AMOUNT);
        assertThat(testFxDeal.getDealTimestamp()).isEqualTo(DEFAULT_DEAL_TIMESTAMP);
        assertThat(testFxDeal.getFromCurrency()).isEqualTo(DEFAULT_FROM_CURRENCY);
        assertThat(testFxDeal.getToCurrency()).isEqualTo(DEFAULT_TO_CURRENCY);
    }

    @Test
    @Transactional
    void getAllFxDealByUniqueId() throws Exception {
        // Initialize the database
        fxDeal.setUniqueId(UUID.randomUUID().toString());
        fxDealRepository.saveAndFlush(fxDeal);

        // Get all the fx deals
        restFxDealMockMvc
                .perform(get("/deals/get/"+ fxDeal.getUniqueId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.dealAmount").value(DEFAULT_DEAL_AMOUNT.intValue()))
                .andExpect(jsonPath("$.fromCurrency").value(DEFAULT_FROM_CURRENCY.getCurrencyCode()))
                .andExpect(jsonPath("$.toCurrency").value(DEFAULT_TO_CURRENCY.getCurrencyCode()));
    }

    @Test
    @Transactional
    void getAllFxDeals() throws Exception {
        // Initialize the database
        fxDeal.setUniqueId(UUID.randomUUID().toString());
        fxDealRepository.saveAndFlush(fxDeal);

        // Get all the fx deals
        restFxDealMockMvc
                .perform(get("/deals/fx-deals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.[*].dealAmount").value(hasItem(DEFAULT_DEAL_AMOUNT.intValue())))
                .andExpect(jsonPath("$.content.[*].fromCurrency").value(hasItem(DEFAULT_FROM_CURRENCY.getCurrencyCode())))
                .andExpect(jsonPath("$.content.[*].toCurrency").value(hasItem(DEFAULT_TO_CURRENCY.getCurrencyCode())));
    }
}
