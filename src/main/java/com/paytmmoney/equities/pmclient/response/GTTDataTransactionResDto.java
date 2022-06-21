package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTDataTransactionResDto {

    @JsonProperty(CommonConstants.EXECUTION_REF_ID)
    private String executionRefId;

    @JsonProperty(CommonConstants.LIMIT_PRICE)
    private double limitPrice;

    @JsonProperty(CommonConstants.NOTIFICATION_REF_ID)
    private String notificationRefId;

    @JsonProperty(CommonConstants.QUANTITY)
    private int quantity;

    @JsonProperty(CommonConstants.SUB_TYPE)
    private String subType;

    @JsonProperty(CommonConstants.TRIGGER_PRICE)
    private double triggerPrice;

    @JsonProperty(CommonConstants.TRIGGERED_AT)
    private String triggeredAt;

    @JsonProperty(CommonConstants.TRIGGERED_AT_PRICE)
    private double triggeredAtPrice;

    @JsonProperty(CommonConstants.TRIGGERED_AT_TYPE)
    private String triggeredAtType;
}
