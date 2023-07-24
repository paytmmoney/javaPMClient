package com.paytmmoney.equities.pmclient.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EpochConverterUtilTest {

    @Test
    public void testEpochConverter() {
        long result = EpochConverterUtil.epochConverter(0L);
        Assert.assertEquals(result, 0L);
    }

    @Test
    public void testEpochConverter1() {
        long result = EpochConverterUtil.epochConverter(1686814077L);
        Assert.assertEquals(result, 2002346877L);
    }
}
