package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTAggregateDataResDto {

    @JsonProperty(ApiConstants.PENDING)
    private List<GTTAggregateStatusResDto> pending;

    @JsonProperty(ApiConstants.TRIGGERED)
    private List<GTTAggregateStatusResDto> triggered;
}
