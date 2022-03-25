
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
public class PositionDataDto {

    @JsonProperty("buy_avg")
    private Double buyAvg;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("cost_price")
    private Double costPrice;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("display_pos_status")
    private String displayPosStatus;
    @JsonProperty("display_pos_type")
    private String displayPosType;
    @JsonProperty("display_product")
    private String displayProduct;
    private String exchange;
    @JsonProperty("expiry_date")
    private String expiryDate;
    private String instrument;
    private String isin;
    @JsonProperty("last_traded_price")
    private Double lastTradedPrice;
    @JsonProperty("lot_size")
    private Long lotSize;
    @JsonProperty("mkt_type")
    private String mktType;
    @JsonProperty("net_avg")
    private Double netAvg;
    @JsonProperty("net_qty")
    private Long netQty;
    @JsonProperty("net_val")
    private Double netVal;
    @JsonProperty("opt_type")
    private String optType;
    private String product;
    @JsonProperty("realised_profit")
    private Double realisedProfit;
    @JsonProperty("security_id")
    private String securityId;
    private String segment;
    @JsonProperty("sell_avg")
    private Double sellAvg;
    @JsonProperty("strike_price")
    private Double strikePrice;
    @JsonProperty("tick_size")
    private Double tickSize;
    @JsonProperty("tot_buy_qty")
    private Long totBuyQty;
    @JsonProperty("tot_buy_qty_cf")
    private Long totBuyQtyCf;
    @JsonProperty("tot_buy_qty_day")
    private Long totBuyQtyDay;
    @JsonProperty("tot_buy_val")
    private Double totBuyVal;
    @JsonProperty("tot_buy_val_cf")
    private Double totBuyValCf;
    @JsonProperty("tot_buy_val_day")
    private Double totBuyValDay;
    @JsonProperty("tot_sell_qty")
    private Long totSellQty;
    @JsonProperty("tot_sell_qty_cf")
    private Long totSellQtyCf;
    @JsonProperty("tot_sell_qty_day")
    private Long totSellQtyDay;
    @JsonProperty("tot_sell_val")
    private Double totSellVal;
    @JsonProperty("tot_sell_val_cf")
    private Double totSellValCf;
    @JsonProperty("tot_sell_val_day")
    private Double totSellValDay;
    @JsonProperty("instrument_type")
    private String instrumentType;
}
