package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTOrderReqDto {

    @JsonProperty(ApiConstants.EXCHANGE)
    private String exchange;

    @Nullable
    @JsonProperty(ApiConstants.PML_ID)
    private String pmlId;

    @JsonProperty(ApiConstants.PRODUCT_TYPE)
    private String productType;

    @JsonProperty(ApiConstants.SECURITY_ID)
    private String securityId;

    @JsonProperty(ApiConstants.SEGMENT)
    private String segment;

    @JsonProperty(ApiConstants.SET_PRICE)
    private String setPrice;

    @JsonProperty(ApiConstants.TRANSACTION_DETAILS)
    private List<GTTTransactionDetailsReqDTO> transactionDetails;

    @JsonProperty(ApiConstants.TRANSACTION_TYPE)
    private String transactionType;

    @JsonProperty(ApiConstants.TRIGGER_TYPE)
    private String triggerType;
}
