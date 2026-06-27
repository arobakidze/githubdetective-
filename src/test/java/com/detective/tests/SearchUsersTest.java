package com.detective.tests;

import com.detective.api.SearchUsersApi;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchUsersTest {

    private String validQuery;
    private String emptyQuery;

    @BeforeClass
    public void setUp() {
        validQuery = "arobakidze";
        emptyQuery = "aaaaabbbbbccccc_nobody_xyz_12345";
    }

    @Test
    public void testSearchValidQueryReturns200() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testSearchValidQueryHasTotalCount() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testSearchValidQueryHasItems() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testSearchValidQueryItemHasId() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testSearchNoResultsStillReturns200() {
        SearchUsersApi api = new SearchUsersApi(emptyQuery);
        api.setResponseTemplate("api/github/search_users/_get/rs_zero.json");
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }
}
