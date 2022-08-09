package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class GTTTransactionDetailsReqDTO {

    @JsonProperty(ApiConstants.LIMIT_PRICE)
    private Double limitPrice;

    @JsonProperty(ApiConstants.QUANTITY)
    private Integer quantity;

    @JsonProperty(ApiConstants.TRIGGER_PRICE)
    private Double triggerPrice;

}
