package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceChartReqDto {

    @JsonProperty(ApiConstants.CONT)
    private boolean cont;

    @JsonProperty(ApiConstants.EXCHANGE)
    private String exchange;

    @JsonProperty(ApiConstants.EXPIRY)
    private String expiry;

    @JsonProperty(ApiConstants.FROM_DATE)
    private String fromDate;

    @JsonProperty(ApiConstants.INSTRUMENT_TYPE)
    private String instType;

    @JsonProperty(ApiConstants.INTERVAL)
    private String interval;

    @JsonProperty(ApiConstants.MONTH_ID)
    private String monthId;

    @JsonProperty(ApiConstants.SERIES)
    private String series;

    @JsonProperty(ApiConstants.STRIKE)
    private String strike;

    @JsonProperty(ApiConstants.SYMBOL)
    private String symbol;

    @JsonProperty(ApiConstants.TO_DATE)
    private String toDate;
}
