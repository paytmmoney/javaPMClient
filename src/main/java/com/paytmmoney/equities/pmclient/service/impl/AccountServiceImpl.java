package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.response.*;
import com.paytmmoney.equities.pmclient.service.AccountService;
import com.paytmmoney.equities.pmclient.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AccountServiceImpl implements AccountService {

    private RestTemplate restTemplate = null;

    public AccountServiceImpl() {
        restTemplate = new RestTemplate();
    }

    public UserDetailsResDto getUserDetails(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<UserDetailsResDto> response = null;
        try {
            response = restTemplate.exchange(
                    ApiConstants.USER_DETAILS_URL, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), UserDetailsResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getUserDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderBookDto getOrderBook(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<OrderBookDto> response = null;
        try {
            response = restTemplate.exchange(
                    ApiConstants.ORDER_BOOK_ENDPOINT, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), OrderBookDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getOrderBook:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public TradeDetailsDto getTradeDetails(SessionManager sessionManager, String orderNo,
                                           String legNo, String segment) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<TradeDetailsDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getTradeDetailsEndpoint(orderNo, legNo, segment),
                    HttpMethod.GET, ApiUtils.getHttpEntity(sessionManager.getAccessToken()), TradeDetailsDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getTradeDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public PositionDto getPosition(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<PositionDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.POSITION_ENDPOINT,
                    HttpMethod.GET, ApiUtils.getHttpEntity(sessionManager.getAccessToken()), PositionDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getPosition:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public PositionDetailDto getPositionDetails(SessionManager sessionManager, String securityId,
                                                String product, String exchange) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<PositionDetailDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getPositionDetailsEndpoint(securityId, product, exchange),
                    HttpMethod.GET, ApiUtils.getHttpEntity(sessionManager.getAccessToken()), PositionDetailDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getPositionDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public HoldingValueDto getHoldingsValue(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<HoldingValueDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.HOLDINGS_VALUE_ENDPOINT, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), HoldingValueDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getHoldingsValue:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public UserHoldingDto getHoldingsData(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<UserHoldingDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.HOLDINGS_DATA_ENDPOINT, HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), UserHoldingDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getHoldingsData:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public FundSummaryDto getFundSummary(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<FundSummaryDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getFundSummaryEndpoint(), HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), FundSummaryDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getFundSummary:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderMarginCalDto getOrderMarginCalculator(SessionManager sessionManager, String source,
                                                      String exchange, String segment, String securityId,
                                                      String txnType, long qty, double price, String product,
                                                      double triggerPrice) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<OrderMarginCalDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getOrderMarginCalculatorEndpoint(source, exchange,
                            segment, securityId, txnType, qty, price, product, triggerPrice), HttpMethod.GET,
                    ApiUtils.getHttpEntity(sessionManager.getAccessToken()), OrderMarginCalDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getOrderMarginCalculator:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }


    public ScriptMarginCalResDto postScriptMarginCalculator(
            SessionManager sessionManager, ScriptMarginCalReqDto scriptMarginCalReqDto) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<ScriptMarginCalResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.MARGIN_CALCULATOR_ENDPOINT, HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(sessionManager.getAccessToken(), scriptMarginCalReqDto),
                    ScriptMarginCalResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->postScriptMarginCalculator:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public String getSecurityMaster(SessionManager sessionManager) throws ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.SECURITY_MASTER_ENDPOINT, HttpMethod.GET,
                    ApiUtils.getHttpEntityCsv(sessionManager.getAccessToken()), String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getSecurityMaster:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }
}
