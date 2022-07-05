package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderResDto;
import com.paytmmoney.equities.pmclient.service.GTTService;
import com.paytmmoney.equities.pmclient.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class GTTServiceImpl implements GTTService {

    private RestTemplate restTemplate = null;

    public GTTServiceImpl() { restTemplate = new RestTemplate();}

    public GTTOrderResDto createGTT(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT, HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(sessionManager.getAccessToken(), gttOrderReqDto), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->createGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTT(SessionManager sessionManager, String id) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdEndpoint(id), HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto updateGTT(SessionManager sessionManager, String id, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdEndpoint(id), HttpMethod.PUT,
                    ApiUtils.getHttpEntityForPost(sessionManager.getAccessToken(), gttOrderReqDto), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->updateGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto deleteGTT(SessionManager sessionManager, String id) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdEndpoint(id), HttpMethod.DELETE,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->deleteGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTGetAllResDto getAllGTT(SessionManager sessionManager, @Nullable String pml_id, @Nullable String status) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTGetAllResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getGttByIdOrStatusEndpoint(pml_id, status), HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTGetAllResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getAllGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTAggregateResDto getGTTAggregate(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTAggregateResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_AGGREGATE, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTAggregateResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTAggregate:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTTExpiry(SessionManager sessionManager, String pmlId) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getGttExpiryEndpoint(pmlId), HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTExpiry:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTTByInstructionId(SessionManager sessionManager, String id) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_BY_INSTRUCTION_ID, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTByInstructionId:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }
}
