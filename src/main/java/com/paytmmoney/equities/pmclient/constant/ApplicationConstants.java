package com.paytmmoney.equities.pmclient.constant;

public class ApplicationConstants {

    public static final String DATA = "data";
    public static final String LAST_TRADE_TIME = "last_trade_time";
    public static final String LAST_UPDATE_TIME = "last_update_time";
    /*
    Value of ninetyEightyConstant is derived by -
    final TimeZone utc = TimeZone.getTimeZone("UTC");
    Calendar ninetyEighty = Calendar.getInstance();
    ninetyEighty.clear();
    ninetyEighty.setTimeZone(utc);
    ninetyEighty.set(1980, 0, 1, 0, 0, 0);
    int ninetyEightyConstant = (int) (ninetyEighty.getTimeInMillis() / 1000);
    */
    public static final long NINETY_EIGHTY_CONSTANT = 315532800;

}
