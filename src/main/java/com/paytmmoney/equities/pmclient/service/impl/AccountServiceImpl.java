package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
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
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.USER_DETAILS_URL[1]);
        ResponseEntity<UserDetailsResDto> response = null;
        try {
            response = restTemplate.exchange(
                    ApiConstants.USER_DETAILS_URL[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), UserDetailsResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getUserDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderBookDto getOrderBook(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.ORDER_BOOK_ENDPOINT[1]);
        ResponseEntity<OrderBookDto> response = null;
        try {
            response = restTemplate.exchange(
                    ApiConstants.ORDER_BOOK_ENDPOINT[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), OrderBookDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getOrderBook:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderBookDto getOrders(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.ORDERS_ENDPOINT[1]);
        ResponseEntity<OrderBookDto> response = null;
        try {
            response = restTemplate.exchange(
                    ApiConstants.ORDERS_ENDPOINT[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), OrderBookDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getOrders:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public TradeDetailsDto getTradeDetails(SessionManager sessionManager, String orderNo,
                                           String legNo, String segment) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager,ApiConstants.TRADE_DETAILS_ENDPOINT[1]);
        ResponseEntity<TradeDetailsDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getTradeDetailsEndpoint(orderNo, legNo, segment),
                    HttpMethod.GET, ApiUtils.getHttpEntity(jwtToken), TradeDetailsDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getTradeDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public PositionDto getPosition(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.POSITION_ENDPOINT[1]);
        ResponseEntity<PositionDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.POSITION_ENDPOINT[0][0],
                    HttpMethod.GET, ApiUtils.getHttpEntity(jwtToken), PositionDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getPosition:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public PositionDetailDto getPositionDetails(SessionManager sessionManager, String securityId,
                                                String product, String exchange) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.POSITION_DETAIL_ENDPOINT[1]);
        ResponseEntity<PositionDetailDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getPositionDetailsEndpoint(securityId, product, exchange),
                    HttpMethod.GET, ApiUtils.getHttpEntity(jwtToken), PositionDetailDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getPositionDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public HoldingValueDto getHoldingsValue(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.HOLDINGS_VALUE_ENDPOINT[1]);
        ResponseEntity<HoldingValueDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.HOLDINGS_VALUE_ENDPOINT[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), HoldingValueDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getHoldingsValue:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public UserHoldingDto getHoldingsData(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.HOLDINGS_DATA_ENDPOINT[1]);
        ResponseEntity<UserHoldingDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.HOLDINGS_DATA_ENDPOINT[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), UserHoldingDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getHoldingsData:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public FundSummaryDto getFundSummary(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.FUND_SUMMARY_ENDPOINT[1]);
        ResponseEntity<FundSummaryDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getFundSummaryEndpoint(), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), FundSummaryDto.class);
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
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.ORDER_MARGIN_CALCULATOR_ENDPOINT[1]);
        ResponseEntity<OrderMarginCalDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getOrderMarginCalculatorEndpoint(source, exchange,
                            segment, securityId, txnType, qty, price, product, triggerPrice), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), OrderMarginCalDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getOrderMarginCalculator:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }


    public ScriptMarginCalResDto postScriptMarginCalculator(
            SessionManager sessionManager, ScriptMarginCalReqDto scriptMarginCalReqDto) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.MARGIN_CALCULATOR_ENDPOINT[1]);
        ResponseEntity<ScriptMarginCalResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.MARGIN_CALCULATOR_ENDPOINT[0][0], HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, scriptMarginCalReqDto),
                    ScriptMarginCalResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->postScriptMarginCalculator:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public String getSecurityMaster(String fileName) throws Exception {
        if (fileName.isEmpty()){
            throw new Exception(MessageConstants.FILE_NAME_NULL);
        }
        ResponseEntity<String> response = null;
        try {
            String scripType = null;
            response = restTemplate.exchange(ApiUtils.getSecurityMasterEndpoint(fileName), HttpMethod.GET,
                    ApiUtils.getHttpEntity(), String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in AccountServiceImpl->getSecurityMaster:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }
}
