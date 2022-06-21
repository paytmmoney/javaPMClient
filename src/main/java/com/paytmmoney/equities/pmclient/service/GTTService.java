package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.response.GTTResDto;

public interface GTTService {

    GTTResDto createGTT(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException;

    GTTResDto getGTT(SessionManager sessionManager) throws ApplicationException;

    GTTResDto updateGTT(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException;

    GTTResDto deleteGTT(SessionManager sessionManager) throws ApplicationException;

    GTTResDto getAllGTT(SessionManager sessionManager) throws ApplicationException;

    GTTResDto getGTTAggregate(SessionManager sessionManager) throws ApplicationException;

    GTTResDto getGTTExpiry(SessionManager sessionManager) throws ApplicationException;

    GTTResDto getGTTByInstructionId(SessionManager sessionManager) throws ApplicationException;
}
