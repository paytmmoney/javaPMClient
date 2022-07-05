package com.paytmmoney.equities.pmclient.util;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisIsin;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDtoList;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.testng.Assert;

import java.util.Arrays;

public class ApiUtilsTest {
    SessionManager sessionManager;

    @Before
    public void setUp() {
        sessionManager = new SessionManager("1", "1", "1");
    }

    @Test
    public void testGetAccessTokenEndpoint() {
        String result = ApiUtils.getAccessTokenEndpoint("api_key", "request_token");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/accounts/v1/gettoken?apiKey=api_key&requestToken=request_token");
    }

    @Test
    public void testGetTradeDetailsEndpoint() {
        String result = ApiUtils.getTradeDetailsEndpoint("101", "1", "E");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/orders/v1/trade-details?order_no=101&leg_no=1&segment=E");
    }

    @Test
    public void testGetPositionDetailsEndpoint() {
        String result = ApiUtils.getPositionDetailsEndpoint("371", "I", "E");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/orders/v1/position-details?security_id=371&product=I&exchange=E");
    }

    @Test
    public void testGetFundSummaryEndpoint() {
        String result = ApiUtils.getFundSummaryEndpoint();
        Assert.assertEquals(result, "https://developer.paytmmoney.com/accounts/v1/funds/summary?config=true");
    }

    @Test
    public void testGetEdisStatuEndpoint() {
        String result = ApiUtils.getEdisStatuEndpoint("10131");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/edis/v1/status?edis_request_id=10131");
    }

    @Test
    public void testGetOrderMarginCalculatorEndpoint() {
        String result = ApiUtils.getOrderMarginCalculatorEndpoint("N", "NSE", "E", "772", "B", 0, 0.0, "I", 0.0);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/margin/v1/order/calculator?source=N&exchange=NSE&segment=E&security_id=772&txn_type=B&quantity=0&price=0.0&product=I&trigger_price=0.0");
    }

    @Test
    public void testGetHttpEntity() {
        HttpEntity<String> result = ApiUtils.getHttpEntity("accessToken");
        Assert.assertEquals(result.getHeaders().getContentType().toString(), "application/json");
    }

    @Test
    public void testGetHttpEntityCsv() {
        HttpEntity<String> result = ApiUtils.getHttpEntityCsv();
        Assert.assertEquals(result.getHeaders().getContentType().toString(), "text/csv");
    }

    @Test
    public void testGetHttpEntityForPost() {
        HttpEntity<String> result = ApiUtils.getHttpEntityForPost("apiSecretKey");
        Assert.assertEquals(result.getHeaders().getContentType().toString(), "application/json");
    }

    @Test
    public void testGetHttpEntityForPost2() {
        HttpEntity<ScriptMarginCalReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new ScriptMarginCalReqDto(Arrays.<ScriptMarginCalReqDtoList>asList(new ScriptMarginCalReqDtoList("exchange", "instrument", "quantity", "securityId", "segment", "strikePrice", "triggerPrice", "txnType")), "source"));
        Assert.assertEquals(result.getBody().getSource().toString(), "source");
    }

//    @Test
//    public void testGetHttpEntityForPost3() {
//        HttpEntity<OrderReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
//        Assert.assertEquals(result.getBody().getClientId().toString(), "clientId");
//    }

    @Test
    public void testGetHttpEntityForPost4() {
        HttpEntity<ConvertOrderReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new ConvertOrderReqDto("exchange", "mktType", "productFrom", "productTo", Long.valueOf(1), "securityId", "segment", "source", "txnType", "transactionId", "clientId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
        Assert.assertEquals(result.getBody().getEdisAuthCode(), "edisAuthCode");
    }

    @Test
    public void testGetHttpEntityForPost5() {
        HttpEntity<EdisValidateReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new EdisValidateReqDto(Arrays.<EdisIsin>asList(new EdisIsin("isin", Long.valueOf(1), true)), "tradeType"));
        Assert.assertEquals(result.getBody().getTradeType(), "tradeType");
    }

    @Test
    public void testIsSessionExpired() throws Exception {
        ApiUtils.isSessionExpired(new SessionManager("apiKey", "apiSecretKey", "accessToken"));
    }

    @Test(expected = ApplicationException.class)
    public void testIsSessionExpiredException() throws Exception {
        SessionManager sessionManager = new SessionManager("apiKey", "apiSecretKey", "accessToken");
        sessionManager.setSessionAlive(false);
        ApiUtils.isSessionExpired(sessionManager);
    }

    @Test(expected = ApplicationException.class)
    public void testHandleException() throws Exception {
        ApiUtils.handleException(null);
    }
}