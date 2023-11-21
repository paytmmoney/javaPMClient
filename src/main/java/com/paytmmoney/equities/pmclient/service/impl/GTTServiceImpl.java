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
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT[0][0], HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, gttOrderReqDto), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->createGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTT(SessionManager sessionManager, String id) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdEndpoint(id), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto updateGTT(SessionManager sessionManager, String id, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager,  ApiConstants.GTT[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdEndpoint(id), HttpMethod.PUT,
                    ApiUtils.getHttpEntityForPost(jwtToken, gttOrderReqDto), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->updateGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto deleteGTT(SessionManager sessionManager, String id) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdEndpoint(id), HttpMethod.DELETE,
                    ApiUtils.getHttpEntity(jwtToken), GTTOrderResDto.class);
            System.out.println(response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->deleteGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTGetAllResDto getAllGTT(SessionManager sessionManager, @Nullable String pml_id, @Nullable String status) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT[1]);
        ResponseEntity<GTTGetAllResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getGttByIdOrStatusEndpoint(pml_id, status), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTGetAllResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getAllGTT:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTAggregateResDto getGTTAggregate(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_AGGREGATE[1]);
        ResponseEntity<GTTAggregateResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_AGGREGATE[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTAggregateResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTAggregate:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTTExpiry(SessionManager sessionManager, String pmlId) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_EXPIRY[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getGttExpiryEndpoint(pmlId), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTExpiry:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTTByInstructionId(SessionManager sessionManager, String id) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_BY_INSTRUCTION_ID[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByInstructionIdEndpoint(id), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTByInstructionId:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto createGTTV2(SessionManager sessionManager, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_V2[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.GTT_V2[0][0], HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, gttOrderReqDto), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->createGTTV2:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTTV2(SessionManager sessionManager, String id) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_V2[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdV2Endpoint(id), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTV2:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto updateGTTV2(SessionManager sessionManager, String id, GTTOrderReqDto gttOrderReqDto) throws
            ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager,  ApiConstants.GTT_V2[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByIdV2Endpoint(id), HttpMethod.PUT,
                    ApiUtils.getHttpEntityForPost(jwtToken, gttOrderReqDto), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->updateGTTV2:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTOrderResDto getGTTByInstructionIdV2(SessionManager sessionManager, String id) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_BY_INSTRUCTION_ID_V2[1]);
        ResponseEntity<GTTOrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.gttByInstructionIdV2Endpoint(id), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTOrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getGTTByInstructionIdV2:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public GTTGetAllResDto getAllGTTV2(SessionManager sessionManager, @Nullable String pml_id, @Nullable String status) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.GTT_V2[1]);
        ResponseEntity<GTTGetAllResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getGttByIdOrStatusV2Endpoint(pml_id, status), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), GTTGetAllResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in GTTServiceImpl->getAllGTTV2:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }
}
