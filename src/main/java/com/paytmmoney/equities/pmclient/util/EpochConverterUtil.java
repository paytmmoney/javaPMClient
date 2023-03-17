package com.paytmmoney.equities.pmclient.util;

import com.paytmmoney.equities.pmclient.constant.ApplicationConstants;
import org.apache.commons.lang3.math.NumberUtils;

public class EpochConverterUtil {

    public static long epochConverter(long inputEpochValue) {
        try {
            if (inputEpochValue > NumberUtils.INTEGER_ZERO){
                return inputEpochValue + ApplicationConstants.NINETY_EIGHTY_CONSTANT;
            }
            return NumberUtils.INTEGER_ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return inputEpochValue;
        }
    }

}
