
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
public class ScriptMarginListResDto {
    @JsonProperty("adhoc_mrg")
    private Double adhocMrg;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("elm_mrg")
    private Double elmMrg;
    private String exchange;
    @JsonProperty("exposure_margin")
    private Double exposureMargin;
    private String instrument;
    private Double price;
    private Long quantity;
    @JsonProperty("row_no")
    private Long rowNo;
    @JsonProperty("security_id")
    private String securityId;
    private String segment;
    @JsonProperty("span_margin")
    private Double spanMargin;
    @JsonProperty("strike_price")
    private Double strikePrice;
    @JsonProperty("total_margin")
    private Double totalMargin;
    @JsonProperty("txn_type")
    private String txnType;
    @JsonProperty("underlaying_security_id")
    private String underlayingSecurityId;
    @JsonProperty("var_elm_margin")
    private Double varElmMargin;
    @JsonProperty("var_mrg")
    private Double varMrg;
}
