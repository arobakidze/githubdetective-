package com.detective.tests;

import com.detective.api.GetRateLimitApi;
import org.testng.annotations.Test;

public class RateLimitTest {

    @Test
    public void testGetRateLimitReturns200() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetRateLimitHasCoreLimit() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse("$.resources.core.limit");
    }

    @Test
    public void testGetRateLimitHasRemaining() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse("$.resources.core.remaining");
    }

    @Test
    public void testGetRateLimitHasSearchLimit() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse("$.resources.search.limit");
    }

    @Test
    public void testGetRateLimitRateLimitIsPositive() {
        GetRateLimitApi api = new GetRateLimitApi();
        api.callAPIExpectSuccess();
        api.validateResponse("$.rate.limit");
    }
}