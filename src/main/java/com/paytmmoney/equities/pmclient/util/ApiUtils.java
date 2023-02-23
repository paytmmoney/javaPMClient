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
import org.apache.commons.lang3.ArrayUtils;
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

    public static String getAccessTokenEndpoint() {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.ACCESS_TOKEN_URL)
                .encode().toUriString();
    }

    public static String getTradeDetailsEndpoint(String orderNo, String legNo, String segment) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.TRADE_DETAILS_ENDPOINT[0][0])
                .queryParam(ApiConstants.ORDER_NO, orderNo)
                .queryParam(ApiConstants.LEG_NO, legNo)
                .queryParam(ApiConstants.SEGMENT, segment)
                .encode().toUriString();
    }

    public static String getPositionDetailsEndpoint(String securityId, String product, String exchange) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.POSITION_DETAIL_ENDPOINT[0][0])
                .queryParam(ApiConstants.SECURITY_ID, securityId)
                .queryParam(ApiConstants.PRODUCT, product)
                .queryParam(ApiConstants.EXCHANGE, exchange)
                .encode().toUriString();
    }

    public static String getFundSummaryEndpoint() {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.FUND_SUMMARY_ENDPOINT[0][0])
                .queryParam(ApiConstants.CONFIG, Boolean.TRUE)
                .encode().toUriString();
    }

    public static String getEdisStatuEndpoint(String edisReqId) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.EDIS_TPIN_STATUS_ENDPOINT[0][0])
                .queryParam(ApiConstants.EDIS_REQUEST_ID, edisReqId)
                .encode().toUriString();
    }

    public static String getOrderMarginCalculatorEndpoint(String source, String exchange, String segment,
                                                          String securityId, String txnType, long qty, double price,
                                                          String product, double triggerPrice) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.ORDER_MARGIN_CALCULATOR_ENDPOINT[0][0])
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

    public static String getSecurityMasterEndpoint(String fileName) {
        String url = ApiConstants.SECURITY_MASTER_ENDPOINT + fileName;
        return UriComponentsBuilder.fromHttpUrl(url).encode().toUriString();
    }

    public static String getGttByIdOrStatusEndpoint(@Nullable String pml_id, @Nullable String status) {
        if (StringUtils.isNotEmpty(pml_id) && StringUtils.isEmpty(status)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT[0][0])
                    .queryParam(ApiConstants.PML_ID_PARAM, pml_id)
                    .encode().toUriString();
        } else if (StringUtils.isEmpty(pml_id) && StringUtils.isNotEmpty(status)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT[0][0])
                    .queryParam(ApiConstants.STATUS, status)
                    .encode().toUriString();
        } else if (StringUtils.isNotEmpty(pml_id) && StringUtils.isNotEmpty(status)) {
            return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT[0][0])
                    .queryParam(ApiConstants.PML_ID_PARAM, pml_id)
                    .queryParam(ApiConstants.STATUS, status)
                    .encode().toUriString();
        }
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT[0][0]).encode().toUriString();
    }

    public static String gttByIdEndpoint(String id) {
        if (StringUtils.isEmpty(id)){
            id = null;
        }
        return ApiConstants.GTT[0][0] + FORWARD_SLASH + id;
    }

    public static String gttByInstructionIdEndpoint(String id) {
        if (StringUtils.isEmpty(id)){
            id = null;
        }
        return ApiConstants.GTT_BY_INSTRUCTION_ID[0][0] + FORWARD_SLASH + id;
    }

    public static String getGttExpiryEndpoint(String pml_id) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.GTT_EXPIRY[0][0])
                .queryParam(ApiConstants.PML_ID_PARAM, pml_id)
                .encode().toUriString();
    }

    public static String getLiveMarketDataEndpoint(String mode, String pref) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.LIVE_MARKET_DATA[0][0])
                .queryParam(ApiConstants.MODE, mode)
                .queryParam(ApiConstants.PREF, pref)
                .encode().toUriString();
    }

    public static String getOptionChainEndpoint(String type,String symbol,String expiry) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.FNO_OPTION_CHAIN[0][0])
                .queryParam(ApiConstants.TYPE, type)
                .queryParam(ApiConstants.SYMBOL, symbol)
                .queryParam(ApiConstants.EXPIRY, expiry)
                .encode().toUriString();
    }

    public static String getOptionChainConfigEndpoint(String symbol) {
        return UriComponentsBuilder.fromHttpUrl(ApiConstants.FNO_OPTION_CHAIN_CONFIG[0][0])
                .queryParam(ApiConstants.SYMBOL, symbol)
                .encode().toUriString();
    }

    public static HttpEntity<String> getHttpEntity(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(ApiConstants.X_JWT_TOKEN, jwtToken);
        return new HttpEntity<>(headers);
    }

    public static HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new HttpEntity<>(headers);
    }

    public static HttpEntity<String> getHttpEntityForPost(String apiKey, String apiSecretKey, String requestToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = new JSONObject();
        json.put(ApiConstants.API_KEY, apiKey);
        json.put(ApiConstants.API_SECRET_KEY,apiSecretKey);
        json.put(ApiConstants.REQUEST_TOKEN,requestToken);
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


    public static String isSessionExpired(SessionManager sessionManager, String[] tokens) throws ApplicationException {
        String jwtToken = null;
        if(ArrayUtils.contains(tokens,ApiConstants.ACCESS_TOKEN) && StringUtils.isNotEmpty(sessionManager.getAccessToken())){
            jwtToken = sessionManager.getAccessToken();
        }
        if(ArrayUtils.contains(tokens,ApiConstants.PUBLIC_ACCESS_TOKEN) && StringUtils.isNotEmpty(sessionManager.getPublicAccessToken())){
            jwtToken = sessionManager.getPublicAccessToken();
        }
        if(ArrayUtils.contains(tokens,ApiConstants.READ_ACCESS_TOKEN) && StringUtils.isNotEmpty(sessionManager.getReadAccessToken())){
            jwtToken = sessionManager.getReadAccessToken();
        }
        if(ArrayUtils.getLength(tokens)>0 && StringUtils.isEmpty(jwtToken)) {
            throw new ApplicationException(MessageConstants.SESSION_EXPIRED, HttpStatus.UNAUTHORIZED.value());
        }
        return jwtToken;
    }

    public static void handleException(ResponseEntity<?> response) throws ApplicationException {
        if (Objects.isNull(response)) {
            throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
        }
        throw new ApplicationException(response.getStatusCode().getReasonPhrase(),
                response.getStatusCode().value());
    }
}
