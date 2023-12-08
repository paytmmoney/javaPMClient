package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GTTOrderDataTransactionV2ResDto implements Serializable {

    @JsonProperty(ApiConstants.EXECUTION_REF_ID)
    private String executionRefId;

    @JsonProperty(ApiConstants.LIMIT_PRICE)
    private Double limitPrice;

    @JsonProperty(ApiConstants.NOTIFICATION_REF_ID)
    private String notificationRefId;

    @JsonProperty(ApiConstants.QUANTITY)
    private Integer quantity;

    @JsonProperty(ApiConstants.SUB_TYPE)
    private String subType;

    @JsonProperty(ApiConstants.TRIGGER_PRICE)
    private Double triggerPrice;

    @JsonProperty(ApiConstants.TRIGGERED_AT)
    private String triggeredAt;

    @JsonProperty(ApiConstants.TRIGGERED_AT_PRICE)
    private Double triggeredAtPrice;

    @JsonProperty(ApiConstants.TRIGGERED_AT_TYPE)
    private String triggeredAtType;

    @JsonProperty(ApiConstants.SET_PRICE)
    private String setPrice;

    @JsonProperty(ApiConstants.ORDER_TYPE)
    private String orderType;

    @JsonProperty(ApiConstants.STATUS)
    private String status;

    @JsonProperty(ApiConstants.ID)
    private String id;
}
