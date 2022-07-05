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
public class GTTOrderDataTransactionResDto implements Serializable {

    @JsonProperty(ApiConstants.EXECUTION_REF_ID)
    private String executionRefId;

    @JsonProperty(ApiConstants.LIMIT_PRICE)
    private double limitPrice;

    @JsonProperty(ApiConstants.NOTIFICATION_REF_ID)
    private String notificationRefId;

    @JsonProperty(ApiConstants.QUANTITY)
    private int quantity;

    @JsonProperty(ApiConstants.SUB_TYPE)
    private String subType;

    @JsonProperty(ApiConstants.TRIGGER_PRICE)
    private double triggerPrice;

    @JsonProperty(ApiConstants.TRIGGERED_AT)
    private String triggeredAt;

    @JsonProperty(ApiConstants.TRIGGERED_AT_PRICE)
    private double triggeredAtPrice;

    @JsonProperty(ApiConstants.TRIGGERED_AT_TYPE)
    private String triggeredAtType;
}
