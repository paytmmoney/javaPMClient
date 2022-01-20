package com.paytmmoney.equities.pmclient.enums;

public enum OrderProductType {
    INTRADAY("T"),
    DELIVERY("C"),
    COVER("V"),
    BRACKET("B"),
    MARGIN("M");

    private String orderType;

    OrderProductType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }
}
