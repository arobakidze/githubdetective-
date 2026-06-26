package com.detective.tests;

import com.detective.api.GetUserApi;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    private String validUsername;
    private String invalidUsername;

    @BeforeClass
    public void setUp() {
        validUsername = System.getProperty("github.username", "arobakidze");
        invalidUsername = "this_user_does_not_exist_xyz_123456789";
    }

    @Test
    public void testGetValidUserReturns200() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetValidUserHasLogin() {
        GetUserApi api = new GetUserApi(validUsername);
        Response response = api.callAPIExpectSuccess();
        Assert.assertEquals(response.jsonPath().getString("login"), validUsername);
    }

    @Test
    public void testGetValidUserHasPublicRepos() {
        GetUserApi api = new GetUserApi(validUsername);
        Response response = api.callAPIExpectSuccess();
        Assert.assertTrue(response.jsonPath().getInt("public_repos") >= 0);
    }

    @Test
    public void testGetValidUserTypeIsUser() {
        GetUserApi api = new GetUserApi(validUsername);
        Response response = api.callAPIExpectSuccess();
        Assert.assertEquals(response.jsonPath().getString("type"), "User");
    }

    @Test
    public void testGetInvalidUserReturns404() {
        GetUserApi api = new GetUserApi(invalidUsername);
        api.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}