
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
public class UserHoldingResultDto {

    @JsonProperty("bse_pml_id")
    private String bsePmlId;
    @JsonProperty("bse_security_id")
    private String bseSecurityId;
    @JsonProperty("bse_symbol")
    private String bseSymbol;
    @JsonProperty("bse_tick_size")
    private String bseTickSize;
    private String cagr;
    @JsonProperty("cost_price")
    private String costPrice;
    @JsonProperty("display_name")
    private String displayName;
    private String exchange;
    @JsonProperty("exchange_inst_name")
    private String exchangeInstName;
    @JsonProperty("is_quantity_mismatch")
    private Boolean isQuantityMismatch;
    @JsonProperty("isin_code")
    private String isinCode;
    @JsonProperty("last_traded_price")
    private String lastTradedPrice;
    @JsonProperty("mcap_type")
    private String mcapType;
    @JsonProperty("nse_pml_id")
    private String nsePmlId;
    @JsonProperty("nse_security_id")
    private String nseSecurityId;
    @JsonProperty("nse_symbol")
    private String nseSymbol;
    @JsonProperty("nse_tick_size")
    private String nseTickSize;
    private Double pc;
    private String quantity;
    @JsonProperty("quantity_mismatch")
    private Long quantityMismatch;
    @JsonProperty("remaining_quantity")
    private String remainingQuantity;
    @JsonProperty("row_no")
    private String rowNo;
    private String sector;
    @JsonProperty("security_source_type")
    private String securitySourceType;
    private String segment;
    @JsonProperty("utilized_quantity")
    private String utilizedQuantity;
    private String xirr;
}
