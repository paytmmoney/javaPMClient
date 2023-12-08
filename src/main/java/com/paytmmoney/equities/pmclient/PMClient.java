package com.paytmmoney.equities.pmclient;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderV2ReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.response.ChargesInfoResDTO;
import com.paytmmoney.equities.pmclient.response.EdisResDto;
import com.paytmmoney.equities.pmclient.response.EdisStatusResDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllV2ResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderV2ResDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDto;
import com.paytmmoney.equities.pmclient.response.LivePriceDataListDto;
import com.paytmmoney.equities.pmclient.response.OptionChainConfigDto;
import com.paytmmoney.equities.pmclient.response.OptionChainDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDto;
import com.paytmmoney.equities.pmclient.response.PositionDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDto;
import com.paytmmoney.equities.pmclient.response.TpinGenerateResDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDto;
import com.paytmmoney.equities.pmclient.response.UserDetailsResDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

@Slf4j
public class PMClient {

    private SessionManager sessionManager = null;
    private SessionManagerService sessionManagerService = null;
    private AccountService accountService = null;
    private OrderService orderService = null;
    private ChartDetailService chartDetailService = null;
    private GTTService gttService = null;


    public PMClient(String apiKey, String apiSecretKey) {
        sessionManager = new SessionManager(apiKey, apiSecretKey);
        sessionManagerService = new SessionManagerServiceImpl();
        accountService = new AccountServiceImpl();
        orderService = new OrderServiceImpl();
        chartDetailService = new ChartDetailServiceImpl();
        gttService = new GTTServiceImpl();
    }

    public PMClient(String apiKey, String apiSecretKey, String accessToken, String publicAccessToken, String readAccessToken) {
        sessionManager = new SessionManager(apiKey, apiSecretKey, accessToken, publicAccessToken, readAccessToken);
        sessionManagerService = new SessionManagerServiceImpl();
        accountService = new AccountServiceImpl();
        orderService = new OrderServiceImpl();
        chartDetailService = new ChartDetailServiceImpl();
        gttService = new GTTServiceImpl();
    }

    public String login(String state_key) {
        return ApiConstants.LOGIN_URL + sessionManager.getApiKey() + ApiConstants.LOGIN_URL_PARAM + state_key;
    }

    public void setAccessToken(String accessToken) {
        sessionManager.setAccessToken(accessToken);
    }

    public void setPublicAccessToken(String publicAccessToken) {
        sessionManager.setPublicAccessToken(publicAccessToken);
    }

    public void setReadAccessToken(String readAccessToken) {
        sessionManager.setReadAccessToken(readAccessToken);
    }

    public String generateSession(String requestToken) throws ApplicationException {
        return sessionManagerService.generateSession(sessionManager, requestToken);
    }

    public UserDetailsResDto getUserDetails() throws ApplicationException {
        return accountService.getUserDetails(sessionManager);
    }

    public String logout() throws ApplicationException {
        return sessionManagerService.logout(sessionManager);
    }

    //Account API
    public OrderBookDto getOrderBook() throws ApplicationException {
        return accountService.getOrderBook(sessionManager);
    }

    public OrderBookDto getOrders() throws ApplicationException {
        return accountService.getOrders(sessionManager);
    }

    public TradeDetailsDto getTradeDetails(String orderNo, String legNo, String segment) throws ApplicationException {
        return accountService.getTradeDetails(sessionManager, orderNo, legNo, segment);
    }

    public PositionDto getPosition() throws ApplicationException {
        return accountService.getPosition(sessionManager);
    }

    public PositionDetailDto getPositionDetails(String securityId, String product, String exchange) throws ApplicationException {
        return accountService.getPositionDetails(sessionManager, securityId, product, exchange);
    }

    public HoldingValueDto getHoldingsValue() throws ApplicationException {
        return accountService.getHoldingsValue(sessionManager);
    }

    public UserHoldingDto getHoldingsData() throws ApplicationException {
        return accountService.getHoldingsData(sessionManager);
    }

    public FundSummaryDto getFundSummary() throws ApplicationException {
        return accountService.getFundSummary(sessionManager);
    }

    public OrderMarginCalDto getOrderMarginCalculator(String source, String exchange, String segment,
                                                      String securityId, String txnType, long qty, double price,
                                                      String product, double triggerPrice) throws ApplicationException {
        return accountService.getOrderMarginCalculator(sessionManager, source, exchange, segment, securityId,
                txnType, qty, price, product, triggerPrice);
    }

    public ScriptMarginCalResDto postScriptMarginCalculator(
            ScriptMarginCalReqDto scriptMarginCalReqDto) throws ApplicationException {
        return accountService.postScriptMarginCalculator(sessionManager, scriptMarginCalReqDto);
    }

    public String getSecurityMaster(String fileName) throws Exception {
        return accountService.getSecurityMaster(fileName);
    }

    //Order API
    public OrderResDto placeOrder(OrderReqDto orderReqDto) throws ApplicationException {
        return orderService.placeOrder(sessionManager, orderReqDto);
    }

    public OrderResDto modifyOrder(OrderReqDto orderReqDto) throws ApplicationException {
        return orderService.modifyOrder(sessionManager, orderReqDto);
    }

    public OrderResDto cancelOrder(OrderReqDto orderReqDto) throws ApplicationException {
        return orderService.cancelOrder(sessionManager, orderReqDto);
    }

    public OrderResDto convertOrder(ConvertOrderReqDto convertOrderReqDto) throws ApplicationException {
        return orderService.convertOrder(sessionManager, convertOrderReqDto);
    }

    //Edis API
    public TpinGenerateResDto generateEdisTpin() throws ApplicationException {
        return orderService.generateEdisTpin(sessionManager);
    }

    public EdisStatusResDto getEdisStatus(String edisReqId) throws ApplicationException {
        return orderService.getEdisStatus(sessionManager, edisReqId);
    }

    public EdisResDto validateEdisTpin(EdisValidateReqDto edisValidateReqDto) throws ApplicationException {
        return orderService.validateEdisTpin(sessionManager, edisValidateReqDto);
    }

//    public PriceChartResDto priceChartDetails(PriceChartReqDto priceChartReqDto) throws ApplicationException {
//        return chartDetailService.priceChartDetails(sessionManager, priceChartReqDto);
//    }

    public ChargesInfoResDTO chargesInfo( String brokerageProfileCode, String transactionType, String instrumentType, String productType, String exchange, Integer qty, Integer price) throws ApplicationException {
        return accountService.chargesInfo(sessionManager, brokerageProfileCode, transactionType, instrumentType, productType, exchange, qty, price);
    }

    //GTT API
    public GTTOrderResDto createGtt(GTTOrderReqDto gttOrderReqDto) throws ApplicationException {
        return gttService.createGTT(sessionManager, gttOrderReqDto);
    }

    public GTTOrderResDto updateGtt(String id, GTTOrderReqDto gttOrderReqDto) throws ApplicationException {
        return gttService.updateGTT(sessionManager, id, gttOrderReqDto);
    }

    public GTTOrderResDto deleteGtt(String id) throws ApplicationException {
        return gttService.deleteGTT(sessionManager, id);
    }

    public GTTOrderResDto getGtt(String id) throws ApplicationException {
        return gttService.getGTT(sessionManager, id);
    }

    public GTTGetAllResDto getAllGtt(@Nullable String pmlId, @Nullable String status) throws ApplicationException {
        return gttService.getAllGTT(sessionManager, pmlId, status);
    }

    public GTTAggregateResDto getGttAggregate() throws ApplicationException {
        return gttService.getGTTAggregate(sessionManager);
    }

    public GTTOrderResDto getGttExpiry(String pmlId) throws ApplicationException {
        return gttService.getGTTExpiry(sessionManager, pmlId);
    }

    public GTTOrderResDto getGttByInstructionId(String id) throws ApplicationException {
        return gttService.getGTTByInstructionId(sessionManager, id);
    }

    public GTTOrderV2ResDto createGttV2(GTTOrderV2ReqDto gttOrderV2ReqDto) throws ApplicationException {
        return gttService.createGTTV2(sessionManager, gttOrderV2ReqDto);
    }

    public GTTOrderV2ResDto updateGttV2(String id, GTTOrderV2ReqDto gttOrderV2ReqDto) throws ApplicationException {
        return gttService.updateGTTV2(sessionManager, id, gttOrderV2ReqDto);
    }

    public GTTOrderV2ResDto getGttV2(String id) throws ApplicationException {
        return gttService.getGTTV2(sessionManager, id);
    }

    public GTTGetAllV2ResDto getAllGttV2(@Nullable String pmlId, @Nullable String status) throws ApplicationException {
        return gttService.getAllGTTV2(sessionManager, pmlId, status);
    }

    public GTTOrderV2ResDto getGttByInstructionIdV2(String id) throws ApplicationException {
        return gttService.getGTTByInstructionIdV2(sessionManager, id);
    }

    public LivePriceDataListDto getLiveMarketData(String mode, String pref) throws ApplicationException {
        return orderService.getLiveMarketData(sessionManager, mode, pref);
    }

    // FNO API
    public OptionChainDto getOptionChain(String type, String symbol, String expiry) throws ApplicationException {
        return orderService.getOptionChain(sessionManager, type, symbol, expiry);
    }

    public OptionChainConfigDto getOptionChainConfig(String symbol) throws ApplicationException {
        return orderService.getOptionChainConfig(sessionManager, symbol);
    }

}
