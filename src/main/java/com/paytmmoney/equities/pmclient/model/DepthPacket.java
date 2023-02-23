package com.paytmmoney.equities.pmclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepthPacket {
    private int packetNo;
    private int buyQuantity;
    private int sellQuantity;
    private short buyOrder;
    private short sellOrder;
    private float buyPrice;
    private float sellPrice;
}
