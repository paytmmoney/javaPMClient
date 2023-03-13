package com.paytmmoney.equities.pmclient.util;

import java.util.Calendar;
import java.util.TimeZone;

public class EpochConverterUtil {

    public static int epochConverter(int inputEpochValue) {
        try {
            final TimeZone utc = TimeZone.getTimeZone("UTC");
            Calendar ninetyEighty = Calendar.getInstance();
            ninetyEighty.clear();
            ninetyEighty.setTimeZone(utc);
            ninetyEighty.set(1980, 0, 1, 0, 0, 0);
            int timeNew = (int) (ninetyEighty.getTimeInMillis() / 1000);
            return inputEpochValue + timeNew;
        } catch (Exception e) {
            e.printStackTrace();
            return inputEpochValue;
        }
    }

}
