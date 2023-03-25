package com.paytmmoney.equities.pmclient.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.ApplicationConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.enums.OrderProductType;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.response.EdisResDto;
import com.paytmmoney.equities.pmclient.response.EdisStatusResDto;
import com.paytmmoney.equities.pmclient.response.LivePriceDataListDto;
import com.paytmmoney.equities.pmclient.response.LivePriceDataDto;
import com.paytmmoney.equities.pmclient.response.OptionChainConfigDto;
import com.paytmmoney.equities.pmclient.response.OptionChainDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.TpinGenerateResDto;
import com.paytmmoney.equities.pmclient.service.OrderService;
import com.paytmmoney.equities.pmclient.util.ApiUtils;
import com.paytmmoney.equities.pmclient.util.EpochConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class OrderServiceImpl implements OrderService {
    private RestTemplate restTemplate = null;

    public OrderServiceImpl() {
        restTemplate = new RestTemplate();
    }

    public OrderResDto placeOrder(SessionManager sessionManager,
                                  OrderReqDto orderReqDto) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.PLACE_BRACKET_ORDER_ENDPOINT[1]);
        ResponseEntity<OrderResDto> response = null;
        try {
            response = restTemplate.exchange(getUrlForPlaceOrder(orderReqDto.getProduct()), HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, orderReqDto), OrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->placeOrder:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderResDto modifyOrder(SessionManager sessionManager,
                                   OrderReqDto orderReqDto) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.BRACKET_MODIFY_ORDER_ENDPOINT[1]);
        ResponseEntity<OrderResDto> response = null;
        try {
            response = restTemplate.exchange(getUrlForModifyOrder(orderReqDto.getProduct()), HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, orderReqDto), OrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->modifyOrder:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderResDto cancelOrder(SessionManager sessionManager,
                                   OrderReqDto orderReqDto) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.BRACKET_CANCEL_ORDER_ENDPOINT[1]);
        ResponseEntity<OrderResDto> response = null;
        try {
            response = restTemplate.exchange(getUrlForCancelOrder(orderReqDto.getProduct()), HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, orderReqDto), OrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->cancelOrder:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public OrderResDto convertOrder(SessionManager sessionManager,
                                    ConvertOrderReqDto convertOrderReqDto) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.REGULAR_CONVERT_ORDER_ENDPOINT[1]);
        ResponseEntity<OrderResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.REGULAR_CONVERT_ORDER_ENDPOINT[0][0], HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, convertOrderReqDto),
                    OrderResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->convertOrder:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public TpinGenerateResDto generateEdisTpin(SessionManager sessionManager) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.EDIS_TPIN_GENERATE_ENDPOINT[1]);
        ResponseEntity<TpinGenerateResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.EDIS_TPIN_GENERATE_ENDPOINT[0][0], HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), TpinGenerateResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->generateEdisTpin:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public EdisStatusResDto getEdisStatus(SessionManager sessionManager, String edisReqId) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.EDIS_TPIN_STATUS_ENDPOINT[1]);
        ResponseEntity<EdisStatusResDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getEdisStatuEndpoint(edisReqId), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken), EdisStatusResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->getEdisStatus:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    public EdisResDto validateEdisTpin(SessionManager sessionManager,
                                       EdisValidateReqDto edisValidateReqDto) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.EDIS_TPIN_VALIDATION_ENDPOINT[1]);
        ResponseEntity<EdisResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.EDIS_TPIN_VALIDATION_ENDPOINT[0][0], HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(jwtToken, edisValidateReqDto),
                    EdisResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->validateEdisTpin:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    @Override
    public LivePriceDataListDto getLiveMarketData(SessionManager sessionManager, String mode, String pref) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.LIVE_MARKET_DATA[1]);
        ResponseEntity<LivePriceDataListDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getLiveMarketDataEndpoint(mode, pref), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken),
                    LivePriceDataListDto.class);
            if (response.getStatusCode() == HttpStatus.OK && ObjectUtils.isNotEmpty(response.getBody())) {
                LivePriceDataListDto responseBody = response.getBody();
                for (LivePriceDataDto livePriceDataDto : responseBody.getData()) {
                    if (ObjectUtils.isNotEmpty(livePriceDataDto.getLastTradeTime())) {
                        livePriceDataDto.setLastTradeTime(EpochConverterUtil.epochConverter(livePriceDataDto.getLastTradeTime()));
                    }
                    if (ObjectUtils.isNotEmpty(livePriceDataDto.getLastUpdateTime())) {
                        livePriceDataDto.setLastUpdateTime(EpochConverterUtil.epochConverter(livePriceDataDto.getLastUpdateTime()));
                    }
                }
                return responseBody;
            }
        } catch (NullPointerException ne) {
            log.error("Exception in OrderServiceImpl->getLiveMarketData:", ne);
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->getLiveMarketData:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    @Override
    public OptionChainDto getOptionChain(SessionManager sessionManager, String type, String symbol, String expiry) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.FNO_OPTION_CHAIN[1]);
        ResponseEntity<OptionChainDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getOptionChainEndpoint(type, symbol, expiry), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken),
                    OptionChainDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->getOptionChain:", e);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    @Override
    public OptionChainConfigDto getOptionChainConfig(SessionManager sessionManager, String symbol) throws ApplicationException {
        String jwtToken = ApiUtils.isSessionExpired(sessionManager, ApiConstants.FNO_OPTION_CHAIN_CONFIG[1]);
        ResponseEntity<OptionChainConfigDto> response = null;
        try {
            response = restTemplate.exchange(ApiUtils.getOptionChainConfigEndpoint(symbol), HttpMethod.GET,
                    ApiUtils.getHttpEntity(jwtToken),
                    OptionChainConfigDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in OrderServiceImpl->getOptionChainConfig:", e);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }

    private String getUrlForPlaceOrder(String productId) {
        if (productId.equals(OrderProductType.BRACKET.getOrderType()))
            return ApiConstants.PLACE_BRACKET_ORDER_ENDPOINT[0][0];

        if (productId.equals(OrderProductType.COVER.getOrderType()))
            return ApiConstants.PLACE_COVER_ORDER_ENDPOINT[0][0];

        return ApiConstants.PLACE_REGULAR_ORDER_ENDPOINT[0][0];
    }

    private String getUrlForModifyOrder(String productId) {
        if (productId.equals(OrderProductType.BRACKET.getOrderType()))
            return ApiConstants.BRACKET_MODIFY_ORDER_ENDPOINT[0][0];

        if (productId.equals(OrderProductType.COVER.getOrderType()))
            return ApiConstants.COVER_MODIFY_ORDER_ENDPOINT[0][0];

        return ApiConstants.REGULAR_MODIFY_ORDER_ENDPOINT[0][0];
    }

    private String getUrlForCancelOrder(String productId) {
        if (productId.equals(OrderProductType.BRACKET.getOrderType()))
            return ApiConstants.BRACKET_CANCEL_ORDER_ENDPOINT[0][0];

        if (productId.equals(OrderProductType.COVER.getOrderType()))
            return ApiConstants.COVER_CANCEL_ORDER_ENDPOINT[0][0];

        return ApiConstants.REGULAR_CANCEL_ORDER_ENDPOINT[0][0];
    }
}
