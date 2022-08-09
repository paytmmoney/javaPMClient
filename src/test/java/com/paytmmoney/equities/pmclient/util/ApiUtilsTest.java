package com.paytmmoney.equities.pmclient.util;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisIsin;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.GTTTransactionDetailsReqDTO;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
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
    public void testGetSecurityMasterEndpoint() {
        String result = ApiUtils.getSecurityMasterEndpoint("scrip_type","exchange");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/data/v1/security-master?scrip_type=scrip_type&exchange=exchange");
    }

    @Test
    public void testGetSecurityMasterEndpoint1() {
        String result = ApiUtils.getSecurityMasterEndpoint(null,"exchange");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/data/v1/security-master?exchange=exchange");
    }

    @Test
    public void testGetSecurityMasterEndpoint2() {
        String result = ApiUtils.getSecurityMasterEndpoint("scrip_type",null);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/data/v1/security-master?scrip_type=scrip_type");
    }

    @Test
    public void testGetSecurityMasterEndpoint3() {
        String result = ApiUtils.getSecurityMasterEndpoint(null,null);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/data/v1/security-master");
    }

    @Test
    public void testGetGttByIdOrStatusEndpoint() {
        String result = ApiUtils.getGttByIdOrStatusEndpoint("pml_id","status");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt?pml-id=pml_id&status=status");
    }

    @Test
    public void testGetGttByIdOrStatusEndpoint1() {
        String result = ApiUtils.getGttByIdOrStatusEndpoint(null,"status");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt?status=status");
    }

    @Test
    public void testGetGttByIdOrStatusEndpoint2() {
        String result = ApiUtils.getGttByIdOrStatusEndpoint("pml_id",null);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt?pml-id=pml_id");
    }

    @Test
    public void testGetGttByIdOrStatusEndpoint3() {
        String result = ApiUtils.getGttByIdOrStatusEndpoint(null,null);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt");
    }

    @Test
    public void testGttByIdEndpoint() {
        String result = ApiUtils.gttByIdEndpoint("id");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt/id");
    }

    @Test
    public void testGttByIdEndpoint1() {
        String result = ApiUtils.gttByIdEndpoint(null);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt/null");
    }

    @Test
    public void testGttByInstructionIdEndpoint() {
        String result = ApiUtils.gttByInstructionIdEndpoint("id");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt/instructions/id");
    }

    @Test
    public void testGttByInstructionIdEndpoint1() {
        String result = ApiUtils.gttByInstructionIdEndpoint(null);
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt/instructions/null");
    }

    @Test
    public void testGetGttExpiryEndpoint() {
        String result = ApiUtils.getGttExpiryEndpoint("pml-id");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/gtt/v1/gtt/expiry-date?pml-id=pml-id");
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
        HttpEntity<PriceChartReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new PriceChartReqDto(false,"exchange","expiry", "fromDate", "instType", "interval","monthId","series","strike","symbol","toDate"));
        Assert.assertEquals(result.getBody().getExchange(), "exchange");
    }

    @Test
    public void testGetHttpEntityForPost6() {
        HttpEntity<GTTOrderReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new GTTOrderReqDto("exchange","orderType","pmlId","productType","securityId","segment","setPrice",Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0.0d),Integer.valueOf(0),Double.valueOf(0.0d))),"transactionType","triggerType"));
        Assert.assertEquals(result.getBody().getPmlId(), "pmlId");
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