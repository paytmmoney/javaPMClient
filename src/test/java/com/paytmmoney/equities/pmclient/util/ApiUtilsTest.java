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
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.testng.Assert;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class ApiUtilsTest {
    SessionManager sessionManager;

    @Mock
    OrderReqDto orderReqDto;
    @Mock
    EdisValidateReqDto edisValidateReqDto;

    @Before
    public void setUp() {
        sessionManager = new SessionManager("1", "1");
    }

    @Test
    public void testGetAccessTokenEndpoint() {
        String result = ApiUtils.getAccessTokenEndpoint();
        Assert.assertEquals(result, "https://developer.paytmmoney.com/accounts/v2/gettoken");
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
        String result = ApiUtils.getSecurityMasterEndpoint("file_name");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/data/v1/scrips/file_name");
    }

    @Test
    public void testGetLiveMarketDataEndpoint() {
        String result = ApiUtils.getLiveMarketDataEndpoint("mode","pref");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/data/v1/price/live?mode=mode&pref=pref");
    }

    @Test
    public void testGetOptionChainEndpoint() {
        String result = ApiUtils.getOptionChainEndpoint("type","symbol","expiry");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/fno/v1/option-chain?type=type&symbol=symbol&expiry=expiry");
    }

    @Test
    public void testGetOptionChainConfigEndpoint() {
        String result = ApiUtils.getOptionChainConfigEndpoint("symbol");
        Assert.assertEquals(result, "https://developer.paytmmoney.com/fno/v1/option-chain/config?symbol=symbol");
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
        HttpEntity<String> result = ApiUtils.getHttpEntity();
        Assert.assertEquals(result.getHeaders().getContentType().toString(), "application/octet-stream");
    }

    @Test
    public void testGetHttpEntity2() {
        HttpEntity<String> result = ApiUtils.getHttpEntity("jwtToken");
    }

    @Test
    public void testGetHttpEntityForPost() {
        HttpEntity<String> result = ApiUtils.getHttpEntityForPost("apiKey", "apiSecret", "requestToken");
    }

    @Test
    public void testGetHttpEntityForPost2() {
        HttpEntity<ScriptMarginCalReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", new ScriptMarginCalReqDto(Arrays.<ScriptMarginCalReqDtoList>asList(new ScriptMarginCalReqDtoList("exchange", "instrument", "quantity", "securityId", "segment", "strikePrice", "triggerPrice", "txnType")), "source"));
        Assert.assertEquals(result.getBody().getSource().toString(), "source");
    }

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
    public void testGetHttpEntityForPost7() {
        HttpEntity<OrderReqDto> result = ApiUtils.getHttpEntityForPost("accessToken", orderReqDto);
    }

    @Test
    public void testGetHttpEntityForPost8() {
        HttpEntity<EdisValidateReqDto> result = ApiUtils.getHttpEntityForPost("token", edisValidateReqDto);
    }

    @Test(expected = ApplicationException.class)
    public void testIsSessionExpiredException() throws ApplicationException {
        String result = ApiUtils.isSessionExpired(sessionManager, new String[]{"access_token", "public_access_token","read_access_token"});
    }

    @Test(expected = ApplicationException.class)
    public void testHandleException() throws Exception {
        ApiUtils.handleException(null);
    }
}