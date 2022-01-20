
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
public class FundsSummary {

    @JsonProperty("adhoc_limit")
    private Double adhocLimit;
    @JsonProperty("funds_added")
    private Double fundsAdded;
    @JsonProperty("opening_balance")
    private Double openingBalance;
    @JsonProperty("trade_balance")
    private Double tradeBalance;
    @JsonProperty("utilised_amount")
    private Double utilisedAmount;
    @JsonProperty("withdrawal_balance")
    private Double withdrawalBalance;
    @JsonProperty("collaterals")
    private Double collaterals;
}
