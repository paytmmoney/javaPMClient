
package com.paytmmoney.equities.pmclient.response;

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
public class ScriptMarginTotalResDto {

    @JsonProperty("t_elm_margin")
    private Double tElmMargin;
    @JsonProperty("t_exposure_margin")
    private Double tExposureMargin;
    @JsonProperty("t_net_opt_premium")
    private Double tNetOptPremium;
    @JsonProperty("t_span_margin")
    private Double tSpanMargin;
    @JsonProperty("t_spread_benefit")
    private Double tSpreadBenefit;
    @JsonProperty("t_tot_adhoc_mrg")
    private Double tTotAdhocMrg;
    @JsonProperty("t_total_margin")
    private Double tTotalMargin;
    @JsonProperty("t_var_elm_margin")
    private Double tVarElmMargin;
    @JsonProperty("t_var_margin")
    private Double tVarMargin;
}
