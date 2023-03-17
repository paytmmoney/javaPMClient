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
    public boolean tradable;
    public String mode;
    public int security_id;
    public double last_price;
    public int last_traded_quantity;
    public double average_traded_price;
    public int volume_traded;
    public int total_buy_quantity;
    public int total_sell_quantity;
    public LivePriceDataOhlcDto ohlc;
    public double change_percent;
    public double change_absolute;
    @JsonProperty("52_week_high")
    public double _52_week_high;
    @JsonProperty("52_week_low")
    public double _52_week_low;
    public long last_trade_time;
    public long last_update_time;
    public int oi;
    public int change_oi;
    public LivePriceDataDepthDto depth;
    public boolean found;

}
