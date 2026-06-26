package com.detective.tests;

import com.detective.api.GetUserReposApi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserReposTest {

    private String validUsername;
    private String invalidUsername;

    @BeforeClass
    public void setUp() {
        validUsername = System.getProperty("github.username", "arobakidze");
        invalidUsername = "this_user_does_not_exist_xyz_123456789";
    }

    @Test
    public void testGetReposValidUserReturns200() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetReposResponseIsArray() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse("$[0].id");
    }

    @Test
    public void testGetReposEachHasName() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse("$[0].name");
    }

    @Test
    public void testGetReposEachHasOwner() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse("$[0].owner.login");
    }

    @Test
    public void testGetReposInvalidUserReturns404() {
        GetUserReposApi api = new GetUserReposApi(invalidUsername);
        api.expectResponseStatus(com.zebrunner.carina.api.http.HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}