
package com.paytmmoney.equities.pmclient.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderReqDto extends CommonOrderReqDto {

    @JsonProperty("trigger_price")
    private Double triggerPrice;
    @JsonProperty("mkt_type")
    private String mktType;
    @JsonProperty("order_no")
    private String orderNo;
    @JsonProperty("serial_no")
    private Integer serialNo;
    @JsonProperty("group_id")
    private Integer groupId;
    @JsonProperty("leg_no")
    private String legNo;
    @JsonProperty("profit_value")
    private Double profitValue;
    @JsonProperty("stoploss_value")
    private Double stopLossValue;
    @JsonProperty("algo_order_no")
    private String algoOrderNo;

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("remarks")
    private String transactionId;


    @JsonProperty(ApiConstants.TAG_TYPE)
    private String tagType;
    @JsonProperty(ApiConstants.TAG_ID)
    private String tagId;
    @JsonProperty(ApiConstants.ALGO_MODULE)
    private String algoModule;
}