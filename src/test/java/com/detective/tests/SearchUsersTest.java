package com.detective.tests;

import com.detective.api.SearchUsersApi;
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
    }

    @Test
    public void testSearchValidQueryHasTotalCount() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse("$.total_count");
    }

    @Test
    public void testSearchValidQueryHasItems() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse("$.items[0].login");
    }

    @Test
    public void testSearchValidQueryItemHasId() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        api.callAPIExpectSuccess();
        api.validateResponse("$.items[0].id");
    }

    @Test
    public void testSearchNoResultsStillReturns200() {
        SearchUsersApi api = new SearchUsersApi(emptyQuery);
        api.callAPIExpectSuccess();
        api.validateResponse("$.total_count", "0");
    }
}