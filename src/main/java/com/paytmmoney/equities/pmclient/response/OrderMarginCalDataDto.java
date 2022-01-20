
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
public class OrderMarginCalDataDto {

    @JsonProperty("available_bal")
    private Double availableBal;
    private Double brokerage;
    @JsonProperty("insufficient_bal")
    private Double insufficientBal;
    @JsonProperty("t_exposure_margin")
    private Double tExposureMargin;
    @JsonProperty("t_span_margin")
    private Double tSpanMargin;
    @JsonProperty("t_total_margin")
    private Double tTotalMargin;
    @JsonProperty("t_var_margin")
    private Double tVarMargin;
}
