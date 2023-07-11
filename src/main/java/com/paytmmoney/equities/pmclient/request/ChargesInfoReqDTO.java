
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
public class ChargesInfoReqDTO {

    @JsonProperty("brokerage_profile_code")
    private String brokerageProfileCode;
    @JsonProperty("transaction_type")
    private String transactionType;
    @JsonProperty("instrument_type")
    private String instrumentType;
    @JsonProperty("product_type")
    private String productType;
    private String exchange;
    private Double qty;
    private Double price;

}