package com.paytmmoney.equities.pmclient;

import com.paytmmoney.equities.pmclient.model.PreferenceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

class LivePriceStreamingExampleTest {
    @Mock
    PMClient pmClient;
    @InjectMocks
    Example example;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testTickerUsage() {
        ArrayList<PreferenceDto> preferenceList = new ArrayList<>();
        preferenceList.add(new PreferenceDto("ADD", "FULL", "EQUITY", "NSE", "3456"));
        example.tickerUsage("your_public_access_token", preferenceList);
    }
}