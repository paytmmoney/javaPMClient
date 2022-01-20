
package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionDetailDataDto {

    @JsonProperty("avg_traded_price")
    private Double avgTradedPrice;
    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;
    @JsonProperty("traded_qty")
    private Long tradedQty;
    @JsonProperty("txn_type")
    private String txnType;
}
