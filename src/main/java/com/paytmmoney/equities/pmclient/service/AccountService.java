package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.response.FundSummaryDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDto;
import com.paytmmoney.equities.pmclient.response.PositionDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDto;
import com.paytmmoney.equities.pmclient.response.UserDetailsResDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDto;

public interface AccountService {
    OrderBookDto getOrderBook(SessionManager sessionManager) throws ApplicationException;

    OrderBookDto getOrders(SessionManager sessionManager) throws ApplicationException;

    UserDetailsResDto getUserDetails(SessionManager sessionManager) throws ApplicationException;

    TradeDetailsDto getTradeDetails(SessionManager sessionManager, String orderNo,
                                    String legNo, String segment) throws ApplicationException;

    PositionDto getPosition(SessionManager sessionManager) throws ApplicationException;

    PositionDetailDto getPositionDetails(SessionManager sessionManager, String securityId,
                                         String product, String exchange) throws ApplicationException;

    HoldingValueDto getHoldingsValue(SessionManager sessionManager) throws ApplicationException;

    UserHoldingDto getHoldingsData(SessionManager sessionManager) throws ApplicationException;

    FundSummaryDto getFundSummary(SessionManager sessionManager) throws ApplicationException;

    OrderMarginCalDto getOrderMarginCalculator(
            SessionManager sessionManager, String source, String exchange, String segment, String securityId,
            String txnType, long qty, double price, String product, double triggerPrice) throws ApplicationException;

    ScriptMarginCalResDto postScriptMarginCalculator(
            SessionManager sessionManager, ScriptMarginCalReqDto scriptMarginCalReqDto) throws ApplicationException;

    String getSecurityMaster(String fileName) throws Exception;
}
