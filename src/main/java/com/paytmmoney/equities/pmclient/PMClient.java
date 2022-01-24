package com.paytmmoney.equities.pmclient;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.response.EdisResDto;
import com.paytmmoney.equities.pmclient.response.EdisStatusResDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDto;
import com.paytmmoney.equities.pmclient.response.PositionDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDto;
import com.paytmmoney.equities.pmclient.response.TpinGenerateResDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDto;
import com.paytmmoney.equities.pmclient.service.AccountService;
import com.paytmmoney.equities.pmclient.service.OrderService;
import com.paytmmoney.equities.pmclient.service.SessionManagerService;
import com.paytmmoney.equities.pmclient.service.impl.AccountServiceImpl;
import com.paytmmoney.equities.pmclient.service.impl.OrderServiceImpl;
import com.paytmmoney.equities.pmclient.service.impl.SessionManagerServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PMClient {

    private SessionManager sessionManager = null;
    private SessionManagerService sessionManagerService = null;
    private AccountService accountService = null;
    private OrderService orderService = null;


    public PMClient(String apiKey, String apiSecretKey) {
        sessionManager = new SessionManager(apiKey, apiSecretKey);
        sessionManagerService = new SessionManagerServiceImpl();
        accountService = new AccountServiceImpl();
        orderService = new OrderServiceImpl();
    }

    public PMClient(String apiKey, String apiSecretKey, String accessToken) {
        sessionManager = new SessionManager(apiKey, apiSecretKey, accessToken);
        sessionManagerService = new SessionManagerServiceImpl();
        accountService = new AccountServiceImpl();
        orderService = new OrderServiceImpl();
    }

    public String login() {
        return ApiConstants.LOGIN_URL + sessionManager.getApiKey();
    }

    public void setAccessToken(String accessToken) {
        sessionManager.setAccessToken(accessToken);
    }

    public String generateSession(String requestToken) throws ApplicationException {
        return sessionManagerService.generateSession(sessionManager, requestToken);
    }

    public String logout() throws ApplicationException {
        return sessionManagerService.logout(sessionManager);
    }

    //Account API
    public OrderBookDto getOrderBook() throws ApplicationException {
        return accountService.getOrderBook(sessionManager);
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

    public String getSecurityMaster() throws ApplicationException {
        return accountService.getSecurityMaster(sessionManager);
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

}
