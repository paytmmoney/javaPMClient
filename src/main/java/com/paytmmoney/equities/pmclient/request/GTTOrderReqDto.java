package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTOrderReqDto {

    @JsonProperty(CommonConstants.EXCHANGE)
    private String exchange;

    @JsonProperty(CommonConstants.ORDER_TYPE)
    private String orderType;

    @JsonProperty(CommonConstants.PML_ID)
    private String pmlId;

    @JsonProperty(CommonConstants.PRODUCT_TYPE)
    private String productType;

    @JsonProperty(CommonConstants.SECURITY_ID)
    private String securityId;

    @JsonProperty(CommonConstants.SEGMENT)
    private String segment;

    @JsonProperty(CommonConstants.SET_PRICE)
    private String setPrice;

    @JsonProperty(CommonConstants.TRANSACTION_DETAILS)
    private List<GTTTransactionDetailsReqDTO> transactionDetails;

    @JsonProperty(CommonConstants.TRANSACTION_TYPE)
    private String transactionType;

    @JsonProperty(CommonConstants.TRIGGER_TYPE)
    private String triggerType;
}
