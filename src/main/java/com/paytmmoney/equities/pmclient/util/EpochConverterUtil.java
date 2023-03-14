package com.paytmmoney.equities.pmclient.util;

import com.paytmmoney.equities.pmclient.constant.ApplicationConstants;

public class EpochConverterUtil {

    public static long epochConverter(long inputEpochValue) {
        try {
            return inputEpochValue + ApplicationConstants.ninetyEightyConstant;
        } catch (Exception e) {
            e.printStackTrace();
            return inputEpochValue;
        }
    }

}
