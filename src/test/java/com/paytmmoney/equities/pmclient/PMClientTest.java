package com.paytmmoney.equities.pmclient;

import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisIsin;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDtoList;
import com.paytmmoney.equities.pmclient.response.EdisDataResDto;
import com.paytmmoney.equities.pmclient.response.EdisDataStatusResDto;
import com.paytmmoney.equities.pmclient.response.EdisIsinResDto;
import com.paytmmoney.equities.pmclient.response.EdisParamResDto;
import com.paytmmoney.equities.pmclient.response.EdisResDto;
import com.paytmmoney.equities.pmclient.response.EdisStatusResDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryConfigDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryCreditDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDataDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDebitDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDto;
import com.paytmmoney.equities.pmclient.response.FundsSummary;
import com.paytmmoney.equities.pmclient.response.GTTDataResDto;
import com.paytmmoney.equities.pmclient.response.GTTDataTransactionResDto;
import com.paytmmoney.equities.pmclient.response.GTTResDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDataDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueResultDto;
import com.paytmmoney.equities.pmclient.response.Meta;
import com.paytmmoney.equities.pmclient.response.OrderBookDataDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDto;
import com.paytmmoney.equities.pmclient.response.OrderDataResDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDataDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.PositionDataDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDataDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDto;
import com.paytmmoney.equities.pmclient.response.PositionDto;
import com.paytmmoney.equities.pmclient.response.PriceChartResDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDataDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginListResDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginTotalResDto;
import com.paytmmoney.equities.pmclient.response.TpinGenerateResDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDataDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDataDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingResultDto;
import com.paytmmoney.equities.pmclient.service.AccountService;
import com.paytmmoney.equities.pmclient.service.ChartDetailService;
import com.paytmmoney.equities.pmclient.service.GTTService;
import com.paytmmoney.equities.pmclient.service.OrderService;
import com.paytmmoney.equities.pmclient.service.SessionManagerService;
import com.paytmmoney.equities.pmclient.service.impl.AccountServiceImpl;
import com.paytmmoney.equities.pmclient.service.impl.ChartDetailServiceImpl;
import com.paytmmoney.equities.pmclient.service.impl.GTTServiceImpl;
import com.paytmmoney.equities.pmclient.service.impl.OrderServiceImpl;
import com.paytmmoney.equities.pmclient.service.impl.SessionManagerServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PMClientTest {

    MockedConstruction<SessionManager> mocked1;
    MockedConstruction<SessionManagerServiceImpl> mocked2;
    MockedConstruction<AccountServiceImpl> mocked3;
    MockedConstruction<OrderServiceImpl> mocked4;
    MockedConstruction<GTTServiceImpl> mocked5;
    MockedConstruction<ChartDetailServiceImpl> mocked6;

    private SessionManager sessionManager;
    private SessionManagerService sessionManagerService;
    private AccountService accountService;
    private OrderService orderService;
    private GTTService gttService;
    private ChartDetailService chartDetailService;
    private PMClient pMClient;

    @Before
    public void setUp() {
        mocked1 = Mockito.mockConstruction(SessionManager.class);
        mocked2 = Mockito.mockConstruction(SessionManagerServiceImpl.class);
        mocked3 = Mockito.mockConstruction(AccountServiceImpl.class);
        mocked4 = Mockito.mockConstruction(OrderServiceImpl.class);
        mocked5 = Mockito.mockConstruction(GTTServiceImpl.class);
        mocked6 = Mockito.mockConstruction(ChartDetailServiceImpl.class);
        pMClient = new PMClient("1", "1","1");
        sessionManager = mocked1.constructed().get(0);
        sessionManagerService = mocked2.constructed().get(0);
        accountService = mocked3.constructed().get(0);
        orderService = mocked4.constructed().get(0);
        gttService = mocked5.constructed().get(0);
        chartDetailService = mocked6.constructed().get(0);
    }

    @After
    public void initMock() {
        mocked1.close();
        mocked2.close();
        mocked3.close();
        mocked4.close();
        mocked5.close();
        mocked6.close();
    }


    @Test
    public void testLogin() {
        new PMClient("1", "1");
        when(sessionManager.getApiKey()).thenReturn("getApiKeyResponse");
        String result = pMClient.login("stateKey");
        Assert.assertEquals(result, "https://login.paytmmoney.com/merchant-login?apiKey=getApiKeyResponse&state=stateKey");
    }

//    @Test(expected = ApplicationException.class)
//    public void testSessionExpired() throws Exception {
//        when(sessionManager.isSessionExpired()).thenReturn(true);
//        pmClient.getOrderBook();
//    }

    @Test
    public void testGetOrderBook() throws Exception {
        when(sessionManager.isSessionExpired()).thenReturn(false);
        when(accountService.getOrderBook(any())).thenReturn(new OrderBookDto(Arrays.<OrderBookDataDto>asList(new OrderBookDataDto("algoOrdNo", 1L, 1L, "clientId", "displayName", "displayOrderType", "displayProduct", "displayStatus", "displayValidity", "errorCode", "exchOrderNo", "exchOrderTime", "exchange", "expiryDate", Integer.valueOf(0), "instrument", "isin", "lastUpdatedTime", "legNo", 1L, "mktType", "offMktFlag", "optType", "orderDateTime", "orderNo", "orderType", "placedBy", "prAbstickValue", (double) 0, "product", 1L, "reasonDescription", (double) 0, 1L, "securityId", "segment", Integer.valueOf(0), "slAbstickValue", "status", "strategyId", (double) 0, 1L, 1L, 1L, "txnType", "validity", "platform", "channel","instrument_type")), "message", "status"));
        OrderBookDto result = pMClient.getOrderBook();
        Assert.assertEquals(result, new OrderBookDto(Arrays.<OrderBookDataDto>asList(new OrderBookDataDto("algoOrdNo", 1L, 1L, "clientId", "displayName", "displayOrderType", "displayProduct", "displayStatus", "displayValidity", "errorCode", "exchOrderNo", "exchOrderTime", "exchange", "expiryDate", Integer.valueOf(0), "instrument", "isin", "lastUpdatedTime", "legNo", 1L, "mktType", "offMktFlag", "optType", "orderDateTime", "orderNo", "orderType", "placedBy", "prAbstickValue", (double) 0, "product", 1L, "reasonDescription", (double) 0, 1L, "securityId", "segment", Integer.valueOf(0), "slAbstickValue", "status", "strategyId", (double) 0, 1L, 1L, 1L, "txnType", "validity", "platform", "channel","instrument_type")), "message", "status"));
    }


    @Test
    public void testSetAccessToken() {
        PMClient pm = new PMClient("1", "1");
        pm.setAccessToken("accessToken");
    }


    @Test
    public void testGenerateSession() throws Exception {
        when(sessionManagerService.generateSession(any(), anyString())).thenReturn(String.valueOf(Arrays.asList("meta", Arrays.asList("requestId", "responseId", "code", "message", "displayMessage"), "data")));
        String result = pMClient.generateSession("requestToken");
        Assert.assertEquals(result, String.valueOf(Arrays.asList("meta", Arrays.asList("requestId", "responseId", "code", "message", "displayMessage"), "data")));
    }

    @Test
    public void testLogout() throws Exception {
        when(sessionManagerService.logout(any())).thenReturn(String.valueOf(Arrays.asList("meta", Arrays.asList("code", "message", "displayMessage", "requestId", "responseId"), "data", Arrays.asList("meta", Arrays.asList("code", "message", "responseId", "requestId", "displayMessage"), "data"))));
        String result = pMClient.logout();
        Assert.assertEquals(result, String.valueOf(Arrays.asList("meta", Arrays.asList("code", "message", "displayMessage", "requestId", "responseId"), "data", Arrays.asList("meta", Arrays.asList("code", "message", "responseId", "requestId", "displayMessage"), "data"))));
    }

    @Test
    public void testGetTradeDetails() throws Exception {
        when(accountService.getTradeDetails(any(), anyString(), anyString(), anyString())).thenReturn(new TradeDetailsDto(Arrays.<TradeDetailsDataDto>asList(new TradeDetailsDataDto("clientId", "exchOrderNo", "exchOrderTime", "exchTradeTime", 1L, "tradeNo", (double) 0)), "message", "status"));
        TradeDetailsDto result = pMClient.getTradeDetails("orderNo", "legNo", "segment");
        Assert.assertEquals(result, new TradeDetailsDto(Arrays.<TradeDetailsDataDto>asList(new TradeDetailsDataDto("clientId", "exchOrderNo", "exchOrderTime", "exchTradeTime", 1L, "tradeNo", (double) 0)), "message", "status"));
    }

    @Test
    public void testGetPosition() throws Exception {
        when(accountService.getPosition(any())).thenReturn(new PositionDto(Arrays.<PositionDataDto>asList(new PositionDataDto((double) 0, "clientId", (double) 0, "displayName", "displayPosStatus", "displayPosType", "displayProduct", "exchange", "expiryDate", "instrument", "isin", (double) 0, 1L, "mktType", (double) 0, 1L, (double) 0, "optType", "product", (double) 0, "securityId", "segment", (double) 0, (double) 0, (double) 0, 1L, 1L, 1L, (double) 0, (double) 0, (double) 0, 1L, 1L, 1L, (double) 0, (double) 0, (double) 0,"instrument_type")), "message", "status"));
        PositionDto result = pMClient.getPosition();
        Assert.assertEquals(result, new PositionDto(Arrays.<PositionDataDto>asList(new PositionDataDto((double) 0, "clientId", (double) 0, "displayName", "displayPosStatus", "displayPosType", "displayProduct", "exchange", "expiryDate", "instrument", "isin", (double) 0, 1L, "mktType", (double) 0, 1L, (double) 0, "optType", "product", (double) 0, "securityId", "segment", (double) 0, (double) 0, (double) 0, 1L, 1L, 1L, (double) 0, (double) 0, (double) 0, 1L, 1L, 1L, (double) 0, (double) 0, (double) 0,"instrument_type")), "message", "status"));
    }

    @Test
    public void testGetPositionDetails() throws Exception {
        when(accountService.getPositionDetails(any(), anyString(), anyString(), anyString())).thenReturn(new PositionDetailDto(Arrays.<PositionDetailDataDto>asList(new PositionDetailDataDto((double) 0, "lastUpdatedTime", 1L, "txnType")), "message", "status"));
        PositionDetailDto result = pMClient.getPositionDetails("772", "I", "NSE");
        Assert.assertEquals(result, new PositionDetailDto(Arrays.<PositionDetailDataDto>asList(new PositionDetailDataDto((double) 0, "lastUpdatedTime", 1L, "txnType")), "message", "status"));
    }

    @Test
    public void testGetHoldingsValue() throws Exception {
        when(accountService.getHoldingsValue(any())).thenReturn(new HoldingValueDto(new HoldingValueDataDto(Arrays.<HoldingValueResultDto>asList(new HoldingValueResultDto("cv", "iv", "timestamp"))), new Meta("displayMessage")));
        HoldingValueDto result = pMClient.getHoldingsValue();
        Assert.assertEquals(result, new HoldingValueDto(new HoldingValueDataDto(Arrays.<HoldingValueResultDto>asList(new HoldingValueResultDto("cv", "iv", "timestamp"))), new Meta("displayMessage")));
    }

    @Test
    public void testGetHoldingsData() throws Exception {
        when(accountService.getHoldingsData(any())).thenReturn(new UserHoldingDto(new UserHoldingDataDto(Collections.<UserHoldingResultDto>singletonList(new UserHoldingResultDto("bsePmlId", "bseSecurityId", "bseSymbol", "bseTickSize", "cagr", "costPrice", "displayName", "exchange", "exchangeInstName", Boolean.TRUE, "isinCode", "lastTradedPrice", "mcapType", "nsePmlId", "nseSecurityId", "nseSymbol", "nseTickSize", (double) 0, "quantity", 1L, "remainingQuantity", "rowNo", "sector", "securitySourceType", "segment", "utilizedQuantity", "xirr", "nse_series", "bse_series"))), new Meta("displayMessage")));
        UserHoldingDto result = pMClient.getHoldingsData();
        Assert.assertEquals(result, new UserHoldingDto(new UserHoldingDataDto(Arrays.<UserHoldingResultDto>asList(new UserHoldingResultDto("bsePmlId", "bseSecurityId", "bseSymbol", "bseTickSize", "cagr", "costPrice", "displayName", "exchange", "exchangeInstName", Boolean.TRUE, "isinCode", "lastTradedPrice", "mcapType", "nsePmlId", "nseSecurityId", "nseSymbol", "nseTickSize", (double) 0, "quantity", 1L, "remainingQuantity", "rowNo", "sector", "securitySourceType", "segment", "utilizedQuantity", "xirr","nseSeries","bse_series"))), new Meta("displayMessage")));
    }

    @Test
    public void testGetFundSummary() throws Exception {
        when(accountService.getFundSummary(any())).thenReturn(new FundSummaryDto(new FundSummaryDataDto(new FundSummaryConfigDto(new FundSummaryCreditDto(1L, 1L, 1L), new FundSummaryDebitDto(1L, 1L, 1L)), new FundsSummary((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0)), new Meta("displayMessage")));
        FundSummaryDto result = pMClient.getFundSummary();
        Assert.assertEquals(result, new FundSummaryDto(new FundSummaryDataDto(new FundSummaryConfigDto(new FundSummaryCreditDto(1L, 1L, 1L), new FundSummaryDebitDto(1L, 1L, 1L)), new FundsSummary((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0)), new Meta("displayMessage")));
    }

    @Test
    public void testGetOrderMarginCalculator() throws Exception {
        when(accountService.getOrderMarginCalculator(any(), anyString(), anyString(), anyString(), anyString(), anyString(), anyLong(), anyDouble(), anyString(), anyDouble())).thenReturn(new OrderMarginCalDto(new OrderMarginCalDataDto((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0), new Meta("displayMessage")));
        OrderMarginCalDto result = pMClient.getOrderMarginCalculator("source", "exchange", "segment", "securityId", "txnType", 0L, 0d, "product", 0d);
        Assert.assertEquals(result, new OrderMarginCalDto(new OrderMarginCalDataDto((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0), new Meta("displayMessage")));
    }

    @Test
    public void testPostScriptMarginCalculator() throws Exception {
        when(accountService.postScriptMarginCalculator(any(), any())).thenReturn(new ScriptMarginCalResDto(new ScriptMarginCalResDataDto(Arrays.<ScriptMarginListResDto>asList(new ScriptMarginListResDto((double) 0, "clientId", (double) 0, "exchange", (double) 0, "instrument", (double) 0, 1L, 1L, "securityId", "segment", (double) 0, (double) 0, (double) 0, "txnType", "underlayingSecurityId", (double) 0, (double) 0)), new ScriptMarginTotalResDto((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0)), new Meta("displayMessage")));
        ScriptMarginCalResDto result = pMClient.postScriptMarginCalculator(new ScriptMarginCalReqDto(Arrays.<ScriptMarginCalReqDtoList>asList(new ScriptMarginCalReqDtoList("exchange", "instrument", "quantity", "securityId", "segment", "strikePrice", "triggerPrice", "txnType")), "source"));
        Assert.assertEquals(result, new ScriptMarginCalResDto(new ScriptMarginCalResDataDto(Collections.<ScriptMarginListResDto>singletonList(new ScriptMarginListResDto((double) 0, "clientId", (double) 0, "exchange", (double) 0, "instrument", (double) 0, 1L, 1L, "securityId", "segment", (double) 0, (double) 0, (double) 0, "txnType", "underlayingSecurityId", (double) 0, (double) 0)), new ScriptMarginTotalResDto((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0)), new Meta("displayMessage")));
    }

    @Test
    public void testGetSecurityMaster() throws Exception {
        when(accountService.getSecurityMaster("scrip_type","exchange")).thenReturn("a, b, c");
        String result = pMClient.getSecurityMaster("scrip_type", "exchange");
        Assert.assertEquals(result, "a, b, c");
    }

    @Test
    public void testPlaceOrder() throws Exception {
        when(orderService.placeOrder(any(), any())).thenReturn(new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
        OrderResDto result = pMClient.placeOrder(new OrderReqDto((double) 0, "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", (double) 0, (double) 0, "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
        Assert.assertEquals(result, new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
    }

    @Test
    public void testModifyOrder() throws Exception {
        when(orderService.modifyOrder(any(), any())).thenReturn(new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
        OrderResDto result = pMClient.modifyOrder(new OrderReqDto((double) 0, "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", (double) 0, (double) 0, "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
        Assert.assertEquals(result, new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
    }

    @Test
    public void testCancelOrder() throws Exception {
        when(orderService.cancelOrder(any(), any())).thenReturn(new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
        OrderResDto result = pMClient.cancelOrder(new OrderReqDto((double) 0, "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", (double) 0, (double) 0, "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
        Assert.assertEquals(result, new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
    }

    @Test
    public void testConvertOrder() throws Exception {
        when(orderService.convertOrder(any(), any())).thenReturn(new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
        OrderResDto result = pMClient.convertOrder(new ConvertOrderReqDto("exchange", "mktType", "productFrom", "productTo", 1L, "securityId", "segment", "source", "txnType", "transactionId", "clientId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
        Assert.assertEquals(result, new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo", "omsErrorCode", "isin", Integer.valueOf(0))), "errorCode", "message", "status", "uuid"));
    }

    @Test
    public void testGenerateEdisTpin() throws Exception {
        when(orderService.generateEdisTpin(any())).thenReturn(new TpinGenerateResDto(new EdisDataResDto(new EdisParamResDto("dPId", "reqId", "transDtls", "version"), "redirectionUrl", "edisRequestId", "tradingDate", Arrays.<EdisIsin>asList(new EdisIsin("isin", 1L, true))), new Meta("displayMessage")));
        TpinGenerateResDto result = pMClient.generateEdisTpin();
        Assert.assertEquals(result, new TpinGenerateResDto(new EdisDataResDto(new EdisParamResDto("dPId", "reqId", "transDtls", "version"), "redirectionUrl", "edisRequestId", "tradingDate", Arrays.<EdisIsin>asList(new EdisIsin("isin", 1L, true))), new Meta("displayMessage")));
    }

    @Test
    public void testGetEdisStatus() throws Exception {
        when(orderService.getEdisStatus(any(), anyString())).thenReturn(new EdisStatusResDto(new EdisDataStatusResDto("redirectionUrl", "tradingDate", Arrays.<EdisIsinResDto>asList(new EdisIsinResDto("isin", true))), new Meta("displayMessage")));
        EdisStatusResDto result = pMClient.getEdisStatus("edisReqId");
        Assert.assertEquals(result, new EdisStatusResDto(new EdisDataStatusResDto("redirectionUrl", "tradingDate", Arrays.<EdisIsinResDto>asList(new EdisIsinResDto("isin", true))), new Meta("displayMessage")));
    }

    @Test
    public void testValidateEdisTpin() throws Exception {
        when(orderService.validateEdisTpin(any(), any())).thenReturn(new EdisResDto(new EdisDataResDto(new EdisParamResDto("dPId", "reqId", "transDtls", "version"), "redirectionUrl", "edisRequestId", "tradingDate", Arrays.<EdisIsin>asList(new EdisIsin("isin", 1L, true))), new Meta("displayMessage")));
        EdisResDto result = pMClient.validateEdisTpin(new EdisValidateReqDto(Arrays.<EdisIsin>asList(new EdisIsin("isin", 1L, true)), "tradeType"));
        Assert.assertEquals(result, new EdisResDto(new EdisDataResDto(new EdisParamResDto("dPId", "reqId", "transDtls", "version"), "redirectionUrl", "edisRequestId", "tradingDate", Arrays.<EdisIsin>asList(new EdisIsin("isin", 1L, true))), new Meta("displayMessage")));
    }

    @Test
    public void testPriceChartDetails() throws Exception {
        when(chartDetailService.priceChartDetails(any(), any())).thenReturn(new PriceChartResDto(Arrays.asList(Arrays.asList("open", "high", "low", "close"))));
        PriceChartResDto result = pMClient.priceChartDetails(new PriceChartReqDto());
        Assert.assertEquals(result, new PriceChartResDto(Arrays.asList(Arrays.asList("open", "high", "low", "close"))));
    }

    @Test
    public void testCreateGtt() throws Exception {
        when(gttService.createGTT(any(), any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.createGtt(new GTTOrderReqDto());
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testUpdateGtt() throws Exception {
        when(gttService.updateGTT(any(), any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.updateGtt("id", new GTTOrderReqDto());
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testDeleteGtt() throws Exception {
        when(gttService.deleteGTT(any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.deleteGtt("id");
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testGetGtt() throws Exception {
        when(gttService.getGTT(any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.getGtt("id");
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testGetAllGtt() throws Exception {
        when(gttService.getAllGTT(any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.getAllGtt("pml_id","status");
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testGetGttAggregate() throws Exception {
        when(gttService.getGTTAggregate(any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.getGttAggregate();
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testGetGttExpiry() throws Exception {
        when(gttService.getGTTExpiry(any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.getGttExpiry("pml_id");
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

    @Test
    public void testGetGttByInstructionId() throws Exception {
        when(gttService.getGTTByInstructionId(any())).thenReturn(new GTTResDto(new GTTDataResDto()));
        GTTResDto result = pMClient.getGttByInstructionId("id");
        Assert.assertEquals(result, new GTTResDto(new GTTDataResDto()));
    }

}

