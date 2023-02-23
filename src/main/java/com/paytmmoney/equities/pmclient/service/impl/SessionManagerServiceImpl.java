package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.response.TokenDataResponseDto;
import com.paytmmoney.equities.pmclient.service.SessionManagerService;
import com.paytmmoney.equities.pmclient.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
public class SessionManagerServiceImpl implements SessionManagerService {

    private RestTemplate restTemplate = null;

    public SessionManagerServiceImpl() {
        restTemplate = new RestTemplate();
    }

    public String generateSession(SessionManager sessionManager, String requestToken) throws ApplicationException {
        String returnMsg = null;
        ResponseEntity<TokenDataResponseDto> response = null;
        try {
            response = restTemplate.exchange(
                    ApiUtils.getAccessTokenEndpoint(),
                    HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(sessionManager.getApiKey(), sessionManager.getApiSecretKey(), requestToken),
                    TokenDataResponseDto.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                TokenDataResponseDto tokenDataResponseDto = response.getBody();
                sessionManager.setAccessToken(tokenDataResponseDto.getAccessToken());
                sessionManager.setPublicAccessToken(tokenDataResponseDto.getPublicAccessToken());
                sessionManager.setReadAccessToken(tokenDataResponseDto.getReadAccessToken());
                returnMsg = response.getStatusCode() + MessageConstants.SESSION_GENERATE_SUCCESSFULLY + requestToken;
            } else {
                returnMsg = response.getStatusCode() + MessageConstants.SESSION_GENERATE_FAILED + requestToken;
            }
        } catch (Exception e) {
            log.error("Exception in SessionManagerServiceImpl->generateSession:", e);
            if (Objects.isNull(response)) {
                throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
            }
            throw new ApplicationException(MessageConstants.SESSION_GENERATE_FAILED +
                    requestToken, response.getStatusCode().value());
        }
        return returnMsg;
    }

    public String logout(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.LOGOUT_ENDPOINT[1]);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.LOGOUT_ENDPOINT[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                sessionManager.setAccessToken(null);
                sessionManager.setPublicAccessToken(null);
                sessionManager.setReadAccessToken(null);
                return response.getStatusCode() + MessageConstants.SESSION_EXPIRED;
            }
            throw new ApplicationException(response.getBody(), response.getStatusCode().value());
        } catch (Exception e) {
            log.error("Exception in SessionManagerServiceImpl->logout:", e);
            if (Objects.isNull(response)) {
                throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
            }
            throw new ApplicationException(response.getBody(), response.getStatusCode().value());
        }
    }
}
