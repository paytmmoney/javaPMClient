package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderV2ReqDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllV2ResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderV2ResDto;
import org.springframework.lang.Nullable;

public interface GTTService {

    GTTOrderResDto createGTT(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException;

    GTTOrderResDto getGTT(SessionManager sessionManager, String id) throws ApplicationException;

    GTTOrderResDto updateGTT(SessionManager sessionManager, String id, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException;

    GTTOrderResDto deleteGTT(SessionManager sessionManager, String id) throws ApplicationException;

    GTTGetAllResDto getAllGTT(SessionManager sessionManager, @Nullable String pml_id, @Nullable String status) throws ApplicationException;

    GTTAggregateResDto getGTTAggregate(SessionManager sessionManager) throws ApplicationException;

    GTTOrderResDto getGTTExpiry(SessionManager sessionManager, String pmlId) throws ApplicationException;

    GTTOrderResDto getGTTByInstructionId(SessionManager sessionManager, String id) throws ApplicationException;

    GTTOrderV2ResDto createGTTV2(SessionManager sessionManager, GTTOrderV2ReqDto gttOrderV2ReqDto) throws ApplicationException;

    GTTOrderV2ResDto getGTTV2(SessionManager sessionManager, String id) throws ApplicationException;

    GTTOrderV2ResDto updateGTTV2(SessionManager sessionManager, String id, GTTOrderV2ReqDto gttOrderV2ReqDto) throws ApplicationException;

    GTTGetAllV2ResDto getAllGTTV2(SessionManager sessionManager, @Nullable String pml_id, @Nullable String status) throws ApplicationException;

    GTTOrderV2ResDto getGTTByInstructionIdV2(SessionManager sessionManager, String id) throws ApplicationException;

}
