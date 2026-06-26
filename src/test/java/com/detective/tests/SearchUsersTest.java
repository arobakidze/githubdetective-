package com.detective.tests;

import com.detective.api.SearchUsersApi;
import io.restassured.response.Response;
import org.testng.Assert;
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
        Response response = api.callAPIExpectSuccess();
        Assert.assertTrue(response.jsonPath().getInt("total_count") > 0);
    }

    @Test
    public void testSearchValidQueryHasItems() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        Response response = api.callAPIExpectSuccess();
        Assert.assertFalse(response.jsonPath().getList("items").isEmpty());
    }

    @Test
    public void testSearchValidQueryItemHasId() {
        SearchUsersApi api = new SearchUsersApi(validQuery);
        Response response = api.callAPIExpectSuccess();
        Assert.assertNotNull(response.jsonPath().getInt("items[0].id"));
    }

    @Test
    public void testSearchNoResultsStillReturns200() {
        SearchUsersApi api = new SearchUsersApi(emptyQuery);
        Response response = api.callAPIExpectSuccess();
        Assert.assertEquals(response.jsonPath().getInt("total_count"), 0);
    }
}