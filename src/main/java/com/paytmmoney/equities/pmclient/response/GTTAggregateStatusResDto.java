package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTAggregateStatusResDto implements Serializable {

    @JsonProperty(ApiConstants.NAME)
    private String name;

    @JsonProperty(ApiConstants.PML_ID)
    private String pmlId;

    @JsonProperty(ApiConstants.COUNT)
    private String count;

    @JsonProperty(ApiConstants.SECURITY_ID)
    private String securityId;

    @JsonProperty(ApiConstants.EXCHANGE)
    private String exchange;

    @JsonProperty(ApiConstants.GTT_INSTRUMENT_TYPE)
    private String instrumentType;

    @JsonProperty(ApiConstants.SEGMENT)
    private String segment;

    @JsonProperty(ApiConstants.LOT_SIZE)
    private String lotSize;

    @JsonProperty(ApiConstants.TICK_SIZE)
    private String tickSize;

}
