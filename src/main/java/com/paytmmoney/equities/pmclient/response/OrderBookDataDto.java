
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
public class OrderBookDataDto {

    @JsonProperty("algo_ord_no")
    private String algoOrdNo;
    @JsonProperty("avg_traded_price")
    private Long avgTradedPrice;
    @JsonProperty("child_leg_unq_id")
    private Long childLegUnqId;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("display_order_type")
    private String displayOrderType;
    @JsonProperty("display_product")
    private String displayProduct;
    @JsonProperty("display_status")
    private String displayStatus;
    @JsonProperty("display_validity")
    private String displayValidity;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("exch_order_no")
    private String exchOrderNo;
    @JsonProperty("exch_order_time")
    private String exchOrderTime;
    private String exchange;
    @JsonProperty("expiry_date")
    private String expiryDate;
    @JsonProperty("group_id")
    private Integer groupId;
    private String instrument;
    private String isin;
    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;
    @JsonProperty("leg_no")
    private String legNo;
    @JsonProperty("lot_size")
    private Long lotSize;
    @JsonProperty("mkt_type")
    private String mktType;
    @JsonProperty("off_mkt_flag")
    private String offMktFlag;
    @JsonProperty("opt_type")
    private String optType;
    @JsonProperty("order_date_time")
    private String orderDateTime;
    @JsonProperty("order_no")
    private String orderNo;
    @JsonProperty("order_type")
    private String orderType;
    @JsonProperty("placed_by")
    private String placedBy;
    @JsonProperty("pr_abstick_value")
    private String prAbstickValue;
    private Double price;
    private String product;
    private Long quantity;
    @JsonProperty("reason_description")
    private String reasonDescription;
    @JsonProperty("ref_ltp")
    private Double refLtp;
    @JsonProperty("remaining_quantity")
    private Long remainingQuantity;
    @JsonProperty("security_id")
    private String securityId;
    private String segment;
    @JsonProperty("serial_no")
    private Integer serialNo;
    @JsonProperty("sl_abstick_value")
    private String slAbstickValue;
    private String status;
    @JsonProperty("strategy_id")
    private String strategyId;
    @JsonProperty("strike_price")
    private Double strikePrice;
    @JsonProperty("tick_size")
    private Long tickSize;
    @JsonProperty("traded_qty")
    private Long tradedQty;
    @JsonProperty("trigger_price")
    private Long triggerPrice;
    @JsonProperty("txn_type")
    private String txnType;
    private String validity;
    private String platform;
    private String channel;
}
