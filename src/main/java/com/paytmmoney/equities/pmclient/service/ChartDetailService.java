package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.PriceChartReqDto;
import com.paytmmoney.equities.pmclient.response.PriceChartResDto;

public interface ChartDetailService {

    PriceChartResDto priceChartDetails(SessionManager sessionManager, PriceChartReqDto priceChartReqDto) throws
            ApplicationException;

}
