package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.request.GTTTransactionDetailsReqDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GTTOrderDataResDto implements Serializable {

    @JsonProperty(ApiConstants.ID)
    private String id;

    @JsonProperty(ApiConstants.INSTRUCTION_ID)
    private String instructionId;

    @JsonProperty(ApiConstants.SEGMENT)
    private String segment;

    @JsonProperty(ApiConstants.EXCHANGE)
    private String exchange;

    @JsonProperty(ApiConstants.SECURITY_ID)
    private String securityId;

    @JsonProperty(ApiConstants.PML_ID)
    private String pmlId;

    @JsonProperty(ApiConstants.NAME)
    private String name;

    @JsonProperty(ApiConstants.USER_ID)
    private String userId;

    @JsonProperty(ApiConstants.STATUS)
    private String status;

    @JsonProperty(ApiConstants.TRANSACTION_TYPE)
    private String transactionType;

    @JsonProperty(ApiConstants.TRANSACTION_DETAILS)
    private List<GTTTransactionDetailsReqDTO> transactionDetails;

    @JsonProperty(ApiConstants.SET_PRICE)
    private String setPrice;

    @JsonProperty(ApiConstants.ORDER_TYPE)
    private String orderType;

    @JsonProperty(ApiConstants.TRIGGER_TYPE)
    private String triggerType;

    @JsonProperty(ApiConstants.PRODUCT_TYPE)
    private String productType;

    @JsonProperty(ApiConstants.CANCELLATION_CODE)
    private String cancellationCode;

    @JsonProperty(ApiConstants.CANCELLATION_REASON)
    private String cancellationReason;

    @JsonProperty(ApiConstants.EXPIRY_DATE)
    private String expiryDate;

    @JsonProperty(ApiConstants.CREATED_AT)
    private String createdAt;

    @JsonProperty(ApiConstants.UPDATED_AT)
    private String updatedAt;

    @JsonProperty(ApiConstants.DELETED_AT)
    private String deletedAt;

    @JsonProperty(ApiConstants.REQUEST_METADATA)
    private String requestMetaData;

}
