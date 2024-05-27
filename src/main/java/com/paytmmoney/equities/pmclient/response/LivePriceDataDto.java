package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LivePriceDataDto {
    private Boolean tradable;
    private String mode;
    @JsonProperty("security_id")
    private Long securityId;
    @JsonProperty("last_price")
    private Double lastPrice;
    @JsonProperty("last_traded_quantity")
    private Long lastTradedQuantity;
    @JsonProperty("average_traded_price")
    private Double averageTradedPrice;
    @JsonProperty("volume_traded")
    private Long volumeTraded;
    @JsonProperty("total_buy_quantity")
    private Long totalBuyQuantity;
    @JsonProperty("total_sell_quantity")
    private Long totalSellQuantity;
    private LivePriceDataOhlcDto ohlc;
    @JsonProperty("change_percent")
    private Double changePercent;
    @JsonProperty("change_absolute")
    private Double changeAbsolute;
    @JsonProperty("52_week_high")
    private Double _52WeekHigh;
    @JsonProperty("52_week_low")
    private Double _52WeekLow;
    @JsonProperty("last_trade_time")
    private Long lastTradeTime;
    @JsonProperty("last_update_time")
    private Long lastUpdateTime;
    private Long oi;
    @JsonProperty("change_oi")
    private Long changeOi;
    private LivePriceDataDepthDto depth;
    private Boolean found;

}
