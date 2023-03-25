package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.response.EdisResDto;
import com.paytmmoney.equities.pmclient.response.EdisStatusResDto;
import com.paytmmoney.equities.pmclient.response.LivePriceDataListDto;
import com.paytmmoney.equities.pmclient.response.OptionChainConfigDto;
import com.paytmmoney.equities.pmclient.response.OptionChainDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.TpinGenerateResDto;

public interface OrderService {

    OrderResDto placeOrder(SessionManager sessionManager, OrderReqDto orderReqDto) throws ApplicationException;

    OrderResDto modifyOrder(SessionManager sessionManager, OrderReqDto orderReqDto) throws ApplicationException;

    OrderResDto cancelOrder(SessionManager sessionManager, OrderReqDto orderReqDto) throws ApplicationException;

    OrderResDto convertOrder(SessionManager sessionManager,
                             ConvertOrderReqDto convertOrderReqDto) throws ApplicationException;

    TpinGenerateResDto generateEdisTpin(SessionManager sessionManager) throws ApplicationException;

    EdisStatusResDto getEdisStatus(SessionManager sessionManager, String edisReqId) throws ApplicationException;

    EdisResDto validateEdisTpin(SessionManager sessionManager,
                                EdisValidateReqDto edisValidateReqDto) throws ApplicationException;

    LivePriceDataListDto getLiveMarketData(SessionManager sessionManager, String mode, String pref) throws ApplicationException;

    OptionChainDto getOptionChain(SessionManager sessionManager, String type, String symbol, String expiry) throws ApplicationException ;

    OptionChainConfigDto getOptionChainConfig(SessionManager sessionManager, String symbol) throws ApplicationException;

}
