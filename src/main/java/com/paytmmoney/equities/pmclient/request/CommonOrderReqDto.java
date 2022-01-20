
package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonOrderReqDto {

    private String source;
    @JsonProperty("txn_type")
    private String txnType;
    private String exchange;
    private String segment;
    private String product;
    @JsonProperty("security_id")
    private String securityId;
    private Long quantity;
    private String validity;
    @JsonProperty("order_type")
    private String orderType;
    private Double price;
    @JsonProperty("off_mkt_flag")
    private String offMktFlag;
}