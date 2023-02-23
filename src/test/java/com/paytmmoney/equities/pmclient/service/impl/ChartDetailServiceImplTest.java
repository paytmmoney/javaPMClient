package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.response.PriceChartResDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChartDetailServiceImplTest {

    MockedConstruction<RestTemplate> mocked;
    RestTemplate restTemplate;
    ChartDetailServiceImpl chartDetailServiceImpl;

    SessionManager sessionManager;

    @Before
    public void setUp() {
        mocked = Mockito.mockConstruction(RestTemplate.class);
        chartDetailServiceImpl = new ChartDetailServiceImpl();
        restTemplate = mocked.constructed().get(0);
        sessionManager = new SessionManager("1", "1","1","1","1");
    }

    @After
    public void initMock() {
        mocked.close();
    }

    @Test
    public void testPriceChartDetails() throws Exception {
        PriceChartResDto priceChartResDto = new PriceChartResDto(Arrays.<List<String>>asList(Arrays.<String>asList("data")));
        ResponseEntity<PriceChartResDto> response = new ResponseEntity<PriceChartResDto>(priceChartResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<PriceChartResDto>>any())
        ).thenReturn(response);
        PriceChartResDto result = chartDetailServiceImpl.priceChartDetails(sessionManager, new PriceChartReqDto(false,"exchange","expiry","fromDate","instType","interval","monthId","series","strike","symbol","toDate"));
        Assert.assertEquals(result.getData().get(0).get(0),"data");
    }

    @Test(expected = ApplicationException.class)
    public void testPriceChartDetailsException() throws Exception {
        PriceChartResDto priceChartResDto = new PriceChartResDto(Arrays.<List<String>>asList(Arrays.<String>asList("data")));
        ResponseEntity<PriceChartResDto> response = new ResponseEntity<PriceChartResDto>(priceChartResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<PriceChartResDto>>any())
        ).thenReturn(null);
        chartDetailServiceImpl.priceChartDetails(sessionManager, new PriceChartReqDto(false,"exchange","expiry","fromDate","instType","interval","monthId","series","strike","symbol","toDate"));
    }
}
