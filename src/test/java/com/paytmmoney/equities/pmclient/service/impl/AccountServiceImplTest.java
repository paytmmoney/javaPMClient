package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDtoList;
import com.paytmmoney.equities.pmclient.response.FundSummaryConfigDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryCreditDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDataDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDebitDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDto;
import com.paytmmoney.equities.pmclient.response.FundsSummary;
import com.paytmmoney.equities.pmclient.response.HoldingValueDataDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueResultDto;
import com.paytmmoney.equities.pmclient.response.Meta;
import com.paytmmoney.equities.pmclient.response.OrderBookDataDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDataDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDto;
import com.paytmmoney.equities.pmclient.response.PositionDataDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDataDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDto;
import com.paytmmoney.equities.pmclient.response.PositionDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDataDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginListResDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginTotalResDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDataDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDataDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingResultDto;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    MockedConstruction<RestTemplate> mocked;
    RestTemplate restTemplate;
    AccountServiceImpl accountServiceImpl;

    SessionManager sessionManager;

    @Before
    public void setUp() {
        mocked = Mockito.mockConstruction(RestTemplate.class);
        accountServiceImpl = new AccountServiceImpl();
        restTemplate = mocked.constructed().get(0);
        sessionManager = new SessionManager("1", "1", "1");
    }

    @After
    public void initMock() {
        mocked.close();
    }

    @Test
    public void testGetOrderBook() throws Exception {
        OrderBookDto orderBookDto = new OrderBookDto(Arrays.<OrderBookDataDto>asList(new OrderBookDataDto("algoOrdNo", 1L, 1L, "clientId", "displayName", "displayOrderType", "displayProduct", "displayStatus", "displayValidity", "errorCode", "exchOrderNo", "exchOrderTime", "exchange", "expiryDate", 0, "instrument", "isin", "lastUpdatedTime", "legNo", 1L, "mktType", "offMktFlag", "optType", "orderDateTime", "orderNo", "orderType", "placedBy", "prAbstickValue", (double) 0, "product", 1L, "reasonDescription", (double) 0, 1L, "securityId", "segment", 0, "slAbstickValue", "status", "strategyId", (double) 0, 1L, 1L, 1L, "txnType", "validity", "platform", "channel","instrument_type","tagType","tagId","algoModule")), "message", "status");
        ResponseEntity<OrderBookDto> response = new ResponseEntity<OrderBookDto>(orderBookDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<OrderBookDto>>any())
        ).thenReturn(response);
        OrderBookDto result = accountServiceImpl.getOrderBook(sessionManager);
        Assert.assertEquals(result.getData().get(0).getOrderNo(), "orderNo");
    }

    @Test(expected = ApplicationException.class)
    public void testGetOrderBookException() throws Exception {
        OrderBookDto orderBookDto = new OrderBookDto(Arrays.<OrderBookDataDto>asList(new OrderBookDataDto("algoOrdNo", 1L, 1L, "clientId", "displayName", "displayOrderType", "displayProduct", "displayStatus", "displayValidity", "errorCode", "exchOrderNo", "exchOrderTime", "exchange", "expiryDate", 0, "instrument", "isin", "lastUpdatedTime", "legNo", 1L, "mktType", "offMktFlag", "optType", "orderDateTime", "orderNo", "orderType", "placedBy", "prAbstickValue", (double) 0, "product", 1L, "reasonDescription", (double) 0, 1L, "securityId", "segment", 0, "slAbstickValue", "status", "strategyId", (double) 0, 1L, 1L, 1L, "txnType", "validity", "platform", "channel","instrument_type","tagType","tagId","algoModule")), "message", "status");
        ResponseEntity<OrderBookDto> response = new ResponseEntity<OrderBookDto>(orderBookDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<OrderBookDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getOrderBook(sessionManager);
    }

    @Test
    public void testGetTradeDetails() throws Exception {
        TradeDetailsDto tradeDetailsDto = new TradeDetailsDto(Arrays.<TradeDetailsDataDto>asList(new TradeDetailsDataDto("clientId", "exchOrderNo", "exchOrderTime", "exchTradeTime", 1L, "tradeNo", (double) 0)), "message", "status");
        ResponseEntity<TradeDetailsDto> response = new ResponseEntity<TradeDetailsDto>(tradeDetailsDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<TradeDetailsDto>>any())
        ).thenReturn(response);
        TradeDetailsDto result = accountServiceImpl.getTradeDetails(sessionManager, "orderNo", "legNo", "segment");
        Assert.assertEquals(result.getData().get(0).getExchOrderNo(), "exchOrderNo");
    }

    @Test(expected = ApplicationException.class)
    public void testGetTradeDetailsExceptionException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<TradeDetailsDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getTradeDetails(sessionManager, "orderNo", "legNo", "segment");
    }

    @Test
    public void testGetPosition() throws Exception {
        PositionDto positionDto = new PositionDto(Arrays.<PositionDataDto>asList(new PositionDataDto((double) 0, "clientId", (double) 0, "displayName", "displayPosStatus", "displayPosType", "displayProduct", "exchange", "expiryDate", "instrument", "isin", (double) 0, 1L, "mktType", (double) 0, 1L, (double) 0, "optType", "product", (double) 0, "securityId", "segment", (double) 0, (double) 0, (double) 0, 1L, 1L, 1L, (double) 0, (double) 0, (double) 0, 1L, 1L, 1L, (double) 0, (double) 0, (double) 0,"instrument_type")), "message", "status");
        ResponseEntity<PositionDto> response = new ResponseEntity<PositionDto>(positionDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<PositionDto>>any())
        ).thenReturn(response);
        PositionDto result = accountServiceImpl.getPosition(sessionManager);
        Assert.assertEquals(result.getData().get(0).getLastTradedPrice(), (double) 0);
    }

    @Test(expected = ApplicationException.class)
    public void testGetPositionExceptionException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<PositionDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getPosition(sessionManager);
    }

    @Test
    public void testGetPositionDetails() throws Exception {
        PositionDetailDto positionDetailDto = new PositionDetailDto(Arrays.<PositionDetailDataDto>asList(new PositionDetailDataDto((double) 0, "lastUpdatedTime", 1L, "txnType")), "message", "status");
        ResponseEntity<PositionDetailDto> response = new ResponseEntity<PositionDetailDto>(positionDetailDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<PositionDetailDto>>any())
        ).thenReturn(response);
        PositionDetailDto result = accountServiceImpl.getPositionDetails(sessionManager, "securityId", "product", "exchange");
        Assert.assertEquals(result.getData().get(0).getTradedQty(), 1L);
    }

    @Test(expected = ApplicationException.class)
    public void testGetPositionDetailsException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<PositionDetailDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getPositionDetails(sessionManager, "securityId", "product", "exchange");
    }

    @Test
    public void testGetHoldingsValue() throws Exception {
        HoldingValueDto holdingValueDto = new HoldingValueDto(new HoldingValueDataDto(Arrays.<HoldingValueResultDto>asList(new HoldingValueResultDto("cv", "iv", "timestamp"))), new Meta("displayMessage"));
        ResponseEntity<HoldingValueDto> response = new ResponseEntity<HoldingValueDto>(holdingValueDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<HoldingValueDto>>any())
        ).thenReturn(response);
        HoldingValueDto result = accountServiceImpl.getHoldingsValue(sessionManager);
        Assert.assertEquals(result.getData().getResults().get(0).getCv(), "cv");
    }

    @Test(expected = ApplicationException.class)
    public void testGetHoldingsValueException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<HoldingValueDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getHoldingsValue(sessionManager);
    }

    @Test
    public void testGetHoldingsData() throws Exception {
        UserHoldingDto userHoldingDto = new UserHoldingDto(new UserHoldingDataDto(Arrays.<UserHoldingResultDto>asList(new UserHoldingResultDto("bsePmlId", "bseSecurityId", "bseSymbol", "bseTickSize", "cagr", "costPrice", "displayName", "exchange", "exchangeInstName", Boolean.TRUE, "isinCode", "lastTradedPrice", "mcapType", "nsePmlId", "nseSecurityId", "nseSymbol", "nseTickSize", (double) 0, "quantity", 1L, "remainingQuantity", "rowNo", "sector", "securitySourceType", "segment", "utilizedQuantity", "xirr","nseSeries","bseSeries"))), new Meta("displayMessage"));
        ResponseEntity<UserHoldingDto> response = new ResponseEntity<UserHoldingDto>(userHoldingDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<UserHoldingDto>>any())
        ).thenReturn(response);
        UserHoldingDto result = accountServiceImpl.getHoldingsData(sessionManager);
        Assert.assertEquals(result.getData().getResults().get(0).getBsePmlId(), "bsePmlId");
    }

    @Test(expected = ApplicationException.class)
    public void testGetHoldingsDataException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<UserHoldingDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getHoldingsData(sessionManager);
    }

    @Test
    public void testGetFundSummary() throws Exception {
        FundSummaryDto fundSummaryDto = new FundSummaryDto(new FundSummaryDataDto(new FundSummaryConfigDto(new FundSummaryCreditDto(1L, 1L, 1L), new FundSummaryDebitDto(1L, 1L, 1L)), new FundsSummary((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0)), new Meta("displayMessage"));
        ResponseEntity<FundSummaryDto> response = new ResponseEntity<FundSummaryDto>(fundSummaryDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<FundSummaryDto>>any())
        ).thenReturn(response);
        FundSummaryDto result = accountServiceImpl.getFundSummary(sessionManager);
        Assert.assertEquals(result.getData().getFundsSummary().getFundsAdded(), (double) 0);
        Assert.assertEquals(result.getData().getConfig().getCredit().getDecimalLength(), 1L);
        Assert.assertEquals(result.getData().getConfig().getDebit().getDecimalLength(), 1L);
    }

    @Test(expected = ApplicationException.class)
    public void testGetFundSummaryException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<FundSummaryDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getFundSummary(sessionManager);
    }

    @Test
    public void testGetOrderMarginCalculator() throws Exception {
        OrderMarginCalDto orderMarginCalDto = new OrderMarginCalDto(new OrderMarginCalDataDto((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0), new Meta("displayMessage"));
        ResponseEntity<OrderMarginCalDto> response = new ResponseEntity<OrderMarginCalDto>(orderMarginCalDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<OrderMarginCalDto>>any())
        ).thenReturn(response);
        OrderMarginCalDto result = accountServiceImpl.getOrderMarginCalculator(sessionManager, "source", "exchange", "segment", "securityId", "txnType", 0L, 0d, "product", 0d);
        Assert.assertEquals(result.getData().getAvailableBal(), (double) 0);
    }

    @Test(expected = ApplicationException.class)
    public void testGetOrderMarginCalculatorException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<OrderMarginCalDto>>any())
        ).thenReturn(null);
        accountServiceImpl.getOrderMarginCalculator(sessionManager, "source", "exchange", "segment", "securityId", "txnType", 0L, 0d, "product", 0d);
    }

    @Test
    public void testPostScriptMarginCalculator() throws Exception {
        ScriptMarginCalResDto scriptMarginCalResDto = new ScriptMarginCalResDto(new ScriptMarginCalResDataDto(Arrays.<ScriptMarginListResDto>asList(new ScriptMarginListResDto((double) 0, "clientId", (double) 0, "exchange", (double) 0, "instrument", (double) 0, 1L, 1L, "securityId", "segment", (double) 0, (double) 0, (double) 0, "txnType", "underlayingSecurityId", (double) 0, (double) 0)), new ScriptMarginTotalResDto((double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0, (double) 0)), new Meta("displayMessage"));
        ResponseEntity<ScriptMarginCalResDto> response = new ResponseEntity<ScriptMarginCalResDto>(scriptMarginCalResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ScriptMarginCalResDto>>any())
        ).thenReturn(response);
        ScriptMarginCalResDto result = accountServiceImpl.postScriptMarginCalculator(sessionManager, new ScriptMarginCalReqDto(Arrays.<ScriptMarginCalReqDtoList>asList(new ScriptMarginCalReqDtoList("exchange", "instrument", "quantity", "securityId", "segment", "strikePrice", "triggerPrice", "txnType")), "source"));
        Assert.assertEquals(result.getData().getMarginList().get(0).getClientId(), "clientId");
    }

    @Test(expected = ApplicationException.class)
    public void testPostScriptMarginCalculatorException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ScriptMarginCalResDto>>any())
        ).thenReturn(null);
        accountServiceImpl.postScriptMarginCalculator(sessionManager, new ScriptMarginCalReqDto(Arrays.<ScriptMarginCalReqDtoList>asList(new ScriptMarginCalReqDtoList("exchange", "instrument", "quantity", "securityId", "segment", "strikePrice", "triggerPrice", "txnType")), "source"));
    }

    @Test
    public void testGetSecurityMaster() throws Exception {
        ResponseEntity<String> response = new ResponseEntity<String>("a,b,c", HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any())
        ).thenReturn(response);
        String result = accountServiceImpl.getSecurityMaster(new ArrayList<>(), "exchange");
        Assert.assertEquals(result, "a,b,c");
    }

    @Test(expected = ApplicationException.class)
    public void testGetSecurityMasterException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any())
        ).thenReturn(null);
        accountServiceImpl.getSecurityMaster(new ArrayList<>(), "exchange");
    }
}