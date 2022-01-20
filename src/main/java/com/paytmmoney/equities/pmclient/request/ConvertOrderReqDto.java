
package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConvertOrderReqDto {

    private String exchange;
    @JsonProperty("mkt_type")
    private String mktType;
    @JsonProperty("product_from")
    private String productFrom;
    @JsonProperty("product_to")
    private String productTo;
    private Long quantity;
    @JsonProperty("security_id")
    private String securityId;
    private String segment;
    private String source;
    @JsonProperty("txn_type")
    private String txnType;
    @JsonProperty("remarks")
    private String transactionId;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("edis_auth_mode")
    private String edisAuthMode;
    @JsonProperty("edis_auth_code")
    private String edisAuthCode;
    @JsonProperty("edis_auth_otp_uuid")
    private UUID edisAuthOtpUuid;
    @JsonProperty("edis_txn_id")
    private String edisTxnId;
}
