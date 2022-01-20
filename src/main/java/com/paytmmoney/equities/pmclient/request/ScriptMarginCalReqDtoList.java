
package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptMarginCalReqDtoList {

    private String exchange;
    private String instrument;
    private String quantity;
    @JsonProperty("security_id")
    private String securityId;
    private String segment;
    @JsonProperty("strike_price")
    private String strikePrice;
    @JsonProperty("trigger_price")
    private String triggerPrice;
    @JsonProperty("txn_type")
    private String txnType;
}
