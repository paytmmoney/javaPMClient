package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.response.GTTResDto;
import com.paytmmoney.equities.pmclient.service.GTTService;
import com.paytmmoney.equities.pmclient.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class GTTServiceImpl implements GTTService {

    private RestTemplate restTemplate = null;

    public GTTServiceImpl() { restTemplate = new RestTemplate();}

    public GTTResDto createGTT(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT, HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(sessionManager.getAccessToken(), gttOrderReqDto), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->createGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto getGTT(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto updateGTT(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT, HttpMethod.PUT,
                    ApiUtils.getHttpEntityForPost(sessionManager.getAccessToken(), gttOrderReqDto), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->updateGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto deleteGTT(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT, HttpMethod.DELETE,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->deleteGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto getAllGTT(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getAllGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto getGTTAggregate(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_AGGREGATE, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTAggregate:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto getGTTExpiry(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_EXPIRY, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTExpiry:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTResDto getGTTByInstructionId(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<GTTResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_BY_INSTRUCTION_ID, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), GTTResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTByInstructionId:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }
}
