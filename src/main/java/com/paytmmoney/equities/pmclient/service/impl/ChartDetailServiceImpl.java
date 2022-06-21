package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.constant.MessageConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.PriceChartResDto;
import com.paytmmoney.equities.pmclient.service.ChartDetailService;
import com.paytmmoney.equities.pmclient.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class ChartDetailServiceImpl implements ChartDetailService {

    private RestTemplate restTemplate = null;

    public ChartDetailServiceImpl() { restTemplate = new RestTemplate();}

    public PriceChartResDto priceChartDetails(SessionManager sessionManager, PriceChartReqDto priceChartReqDto) throws
            ApplicationException {
        ApiUtils.isSessionExpired(sessionManager);
        ResponseEntity<PriceChartResDto> response = null;
        try {
            response = restTemplate.exchange(ApiConstants.PRICE_CHART_DETAIL_ENDPOINT, HttpMethod.POST,
                    ApiUtils.getHttpEntityForPost(sessionManager.getAccessToken(), priceChartReqDto), PriceChartResDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Exception in ChartDetailServiceImpl->priceChartDetails:", e);
            ApiUtils.handleException(response);
        }
        throw new ApplicationException(MessageConstants.NULL_RESPONSE, HttpStatus.NO_CONTENT.value());
    }
}
