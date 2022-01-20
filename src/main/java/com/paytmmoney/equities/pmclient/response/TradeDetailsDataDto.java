
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
public class TradeDetailsDataDto {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("exch_order_no")
    private String exchOrderNo;
    @JsonProperty("exch_order_time")
    private String exchOrderTime;
    @JsonProperty("exch_trade_time")
    private String exchTradeTime;
    private Long quantity;
    @JsonProperty("trade_no")
    private String tradeNo;
    @JsonProperty("traded_price")
    private Double tradedPrice;
}
