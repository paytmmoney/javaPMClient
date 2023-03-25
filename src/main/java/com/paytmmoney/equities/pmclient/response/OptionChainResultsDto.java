package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionChainResultsDto {
    @JsonProperty("pml_id")
    private Long pmlId;
    private String exchange;
    @JsonProperty("underlaying_scrip_code")
    private Long underlayingScripCode;
    private String segment;
    @JsonProperty("security_id")
    private Long securityId;
    @JsonProperty("pml_symbol")
    private String pmlSymbol;
    private String vega;
    @JsonProperty("fresh_pos")
    private String freshPos;
    private String iv;
    @JsonProperty("square_off_pos")
    private String squareOffPos;
    private String desc;
    private String theta;
    private String gamma;
    @JsonProperty("spot_price")
    private String spotPrice;
    private String delta;
    private String price;
    @JsonProperty("stk_price")
    private Double stkPrice;
    @JsonProperty("net_chg")
    private String netChg;
    private String oi;
    @JsonProperty("oi_per_chg")
    private String oiPerChg;
    @JsonProperty("oi_net_chg")
    private String oiNetChg;
    @JsonProperty("per_chg")
    private String perChg;
    @JsonProperty("traded_vol")
    private String tradedVol;
    private String symbol;
    @JsonProperty("expiry_date")
    private String expiryDate;
    @JsonProperty("option_type")
    private String optionType;
    private String instrument;
    private String name;
    @JsonProperty("tick_size")
    private Long tickSize;
    @JsonProperty("lot_size")
    private Long lotSize;
    @JsonProperty("exch_feed_time")
    private String exchFeedTime;

}