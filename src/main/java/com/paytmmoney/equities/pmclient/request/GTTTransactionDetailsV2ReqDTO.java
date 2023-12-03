package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTTransactionDetailsV2ReqDTO {

    @Nullable
    @JsonProperty(ApiConstants.ID)
    private String id;

    @Nullable
    @JsonProperty(ApiConstants.SUB_TYPE)
    private String subType;

    @JsonProperty(ApiConstants.ORDER_TYPE)
    private String orderType;

    @JsonProperty(ApiConstants.LIMIT_PRICE)
    private Double limitPrice;

    @JsonProperty(ApiConstants.QUANTITY)
    private Integer quantity;

    @JsonProperty(ApiConstants.TRIGGER_PRICE)
    private Double triggerPrice;

}
