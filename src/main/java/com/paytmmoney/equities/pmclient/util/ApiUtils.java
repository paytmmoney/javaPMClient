package com.paytmmoney.equities.pmclient.util;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static com.paytmmoney.equities.pmclient.constant.ApiConstants.FORWARD_SLASH;

public class ApiUtils {

    public static String getAccessTokenEndpoint(String apiKey, String requestToken) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.ACCESS_TOKEN_URL)
                .queryParam(ApiConstants.API_KEY, apiKey)
                .queryParam(ApiConstants.REQUEST_TOKEN, requestToken)
                .encode().toUriString();
    }

    public static String getTradeDetailsEndpoint(String orderNo, String legNo, String segment) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.TRADE_DETAILS_ENDPOINT)
                .queryParam(ApiConstants.ORDER_NO, orderNo)
                .queryParam(ApiConstants.LEG_NO, legNo)
                .queryParam(ApiConstants.SEGMENT, segment)
                .encode().toUriString();
    }

    public static String getPositionDetailsEndpoint(String securityId, String product, String exchange) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.POSITION_DETAIL_ENDPOINT)
                .queryParam(ApiConstants.SECURITY_ID, securityId)
                .queryParam(ApiConstants.PRODUCT, product)
                .queryParam(ApiConstants.EXCHANGE, exchange)
                .encode().toUriString();
    }

    public static String getFundSummaryEndpoint() {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.FUND_SUMMARY_ENDPOINT)
                .queryParam(ApiConstants.CONFIG, Boolean.TRUE)
                .encode().toUriString();
    }

    public static String getEdisStatuEndpoint(String edisReqId) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.EDIS_TPIN_STATUS_ENDPOINT)
                .queryParam(ApiConstants.EDIS_REQUEST_ID, edisReqId)
                .encode().toUriString();
    }

    public static String getOrderMarginCalculatorEndpoint(String source, String exchange, String segment,
                                                          String securityId, String txnType, long qty, double price,
                                                          String product, double triggerPrice) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.ORDER_MARGIN_CALCULATOR_ENDPOINT)
                .queryParam(ApiConstants.SOURCE, source)
                .queryParam(ApiConstants.EXCHANGE, exchange)
                .queryParam(ApiConstants.SEGMENT, segment)
                .queryParam(ApiConstants.SECURITY_ID, securityId)
                .queryParam(ApiConstants.TXN_TYPE, txnType)
                .queryParam(ApiConstants.QUANTITY, qty)
                .queryParam(ApiConstants.PRICE, price)
                .queryParam(ApiConstants.PRODUCT, product)
                .queryParam(ApiConstants.TRIGGER_PRICE, triggerPrice)
                .encode().toUriString();
    }

    public static String getSecurityMasterEndpoint(@Nullable String scrip_type, @Nullable String exchange) {
        if (StringUtils.isNotEmpty(scrip_type) && StringUtils.isEmpty(exchange)){
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.SECURITY_MASTER_ENDPOINT)
                    .queryParam(ApiConstants.SCRIP_TYPE, scrip_type)
                    .encode().toUriString();
        } else if (StringUtils.isEmpty(scrip_type) && StringUtils.isNotEmpty(exchange)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.SECURITY_MASTER_ENDPOINT)
                    .queryParam(ApiConstants.EXCHANGE, exchange)
                    .encode().toUriString();
        } else if (StringUtils.isNotEmpty(scrip_type) && StringUtils.isNotEmpty(exchange)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.SECURITY_MASTER_ENDPOINT)
                    .queryParam(ApiConstants.SCRIP_TYPE, scrip_type)
                    .queryParam(ApiConstants.EXCHANGE, exchange)
                    .encode().toUriString();
        }
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.SECURITY_MASTER_ENDPOINT).encode().toUriString();
    }

    public static String getGttByIdOrStatusEndpoint(@Nullable String pml_id, @Nullable String status) {
        if (StringUtils.isNotEmpty(pml_id) && StringUtils.isEmpty(status)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT)
                    .queryParam(ApiConstants.PML_ID_PARAM, pml_id)
                    .encode().toUriString();
        } else if (StringUtils.isEmpty(pml_id) && StringUtils.isNotEmpty(status)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT)
                    .queryParam(ApiConstants.STATUS, status)
                    .encode().toUriString();
        } else if (StringUtils.isNotEmpty(pml_id) && StringUtils.isNotEmpty(status)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT)
                    .queryParam(ApiConstants.PML_ID_PARAM, pml_id)
                    .queryParam(ApiConstants.STATUS, status)
                    .encode().toUriString();
        }
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT).encode().toUriString();
    }

    public static String gttByIdEndpoint(String id) {
        if (StringUtils.isEmpty(id)){
            id = null;
        }
        return ApiConstants.GTT + FORWARD_SLASH + id;
    }

    public static String gttByInstructionIdEndpoint(String id) {
        if (StringUtils.isEmpty(id)){
            id = null;
        }
        return ApiConstants.GTT_BY_INSTRUCTION_ID + FORWARD_SLASH + id;
    }

    public static String getGttExpiryEndpoint(String pml_id) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT_EXPIRY)
                .queryParam(ApiConstants.PML_ID_PARAM, pml_id)
                .encode().toUriString();
    }

    public static HttpEntity<String> getHttpEntity(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(headers);
    }

    public static HttpEntity<String> getHttpEntityCsv() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(ApiConstants.TEXT, ApiConstants.CSV));
//        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(headers);
    }

    public static HttpEntity<String> getHttpEntityForPost(String apiSecretKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = new JSONObject();
        json.put(ApiConstants.MERCHANT_SECRET, apiSecretKey);
        return new HttpEntity<>(json.toString(), headers);
    }

    public static HttpEntity<ScriptMarginCalReqDto> getHttpEntityForPost(
            String accessToken, ScriptMarginCalReqDto scriptMarginCalReqDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(scriptMarginCalReqDto, headers);
    }

    public static HttpEntity<OrderReqDto> getHttpEntityForPost(
            String accessToken, OrderReqDto orderReqDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(orderReqDto, headers);
    }

    public static HttpEntity<ConvertOrderReqDto> getHttpEntityForPost(
            String accessToken, ConvertOrderReqDto convertOrderReqDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(convertOrderReqDto, headers);
    }

    public static HttpEntity<EdisValidateReqDto> getHttpEntityForPost(
            String accessToken, EdisValidateReqDto edisValidateReqDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(edisValidateReqDto, headers);
    }

    public static HttpEntity<PriceChartReqDto> getHttpEntityForPost(
            String accessToken, PriceChartReqDto priceChartReqDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(priceChartReqDto, headers);
    }

    public static HttpEntity<GTTOrderReqDto> getHttpEntityForPost(
            String accessToken, GTTOrderReqDto gttOrderReqDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, accessToken);
        return new HttpEntity<>(gttOrderReqDto, headers);
    }


    public static void isSessionExpired(SessionManager sessionManager) throws ApplicationException {
        if (sessionManager.isSessionExpired()) {
            throw new ApplicationException(MessageConstants.SESSION_EXPIRED, HttpStatus.UNAUTHORIZED.value());
        }
    }

    public static void handleException(ResponseEntity<?> response) throws ApplicationException {
        if (Objects.isNull(response)) {
            throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
        }
        throw new ApplicationException(response.getStatusCode().getReasonPhrase(),
                response.getStatusCode().value());
    }
}
