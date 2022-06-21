package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.CommonConstants;
import com.paytmmoney.equities.pmclient.request.GTTTransactionDetailsReqDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GTTDataResDto {

    @JsonProperty(CommonConstants.ID)
    private String id;

    @JsonProperty(CommonConstants.INSTRUCTION_ID)
    private String instructionId;

    @JsonProperty(CommonConstants.SEGMENT)
    private String segment;

    @JsonProperty(CommonConstants.EXCHANGE)
    private String exchange;

    @JsonProperty(CommonConstants.SECURITY_ID)
    private String securityId;

    @JsonProperty(CommonConstants.PML_ID)
    private String pmlId;

    @JsonProperty(CommonConstants.NAME)
    private String name;

    @JsonProperty(CommonConstants.USER_ID)
    private String userId;

    @JsonProperty(CommonConstants.STATUS)
    private String status;

    @JsonProperty(CommonConstants.TRANSACTION_TYPE)
    private String transactionType;

    @JsonProperty(CommonConstants.TRANSACTION_DETAILS)
    private List<GTTTransactionDetailsReqDTO> transactionDetails;

    @JsonProperty(CommonConstants.SET_PRICE)
    private String setPrice;

    @JsonProperty(CommonConstants.ORDER_TYPE)
    private String orderType;

    @JsonProperty(CommonConstants.TRIGGER_TYPE)
    private String triggerType;

    @JsonProperty(CommonConstants.PRODUCT_TYPE)
    private String productType;

    @JsonProperty(CommonConstants.CANCELLATION_CODE)
    private String cancellationCode;

    @JsonProperty(CommonConstants.CANCELLATION_REASON)
    private String cancellationReason;

    @JsonProperty(CommonConstants.EXPIRY_DATE)
    private String expiryDate;

    @JsonProperty(CommonConstants.CREATED_AT)
    private String createdAt;

    @JsonProperty(CommonConstants.UPDATED_AT)
    private String updatedAt;

    @JsonProperty(CommonConstants.DELETED_AT)
    private String deletedAt;

    @JsonProperty(CommonConstants.REQUEST_METADATA)
    private String requestMetaData;

    @JsonProperty(CommonConstants.META)
    private GTTMetaResDto meta;

}
