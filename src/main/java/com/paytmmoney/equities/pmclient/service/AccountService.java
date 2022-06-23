package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.response.*;
import org.springframework.lang.Nullable;

public interface AccountService {
    OrderBookDto getOrderBook(SessionManager sessionManager) throws ApplicationException;

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

    String getSecurityMaster(@Nullable String scrip_type, @Nullable String exchange) throws ApplicationException;
}
