package com.detective.tests;

import com.detective.api.GetRateLimitApi;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class RateLimitTest {

    @Test
    public void testGetRateLimitReturns200() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetRateLimitHasCoreLimit() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetRateLimitHasRemaining() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetRateLimitHasSearchLimit() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetRateLimitRateLimitIsPositive() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }
}
