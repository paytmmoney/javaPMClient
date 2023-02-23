package com.paytmmoney.equities.pmclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tick {
    private ArrayList<DepthPacket> mbpRowPacket;
    private float lastTradedPrice;
    private int lastTradedTime;
    private int securityId;
    private byte tradable;
    private byte mode;
    private int lastTradedQuantity;
    private float averageTradedPrice;
    private int volumeTraded;
    private int totalBuyQuantity;
    private int totalSellQuantity;
    private float open;
    private float close;
    private float high;
    private float low;
    private float changePercent;
    private float changeAbsolute;
    private float fiftyTwoWeekHigh;
    private float fiftyTwoWeekLow;
    private int oi;
    private int oiChange;
}
