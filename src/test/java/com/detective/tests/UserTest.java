package com.detective.tests;

import com.detective.api.GetUserApi;
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
        api.validateResponseAgainstSchema("api_templates/user_schema.json");
    }

    @Test
    public void testGetValidUserHasLogin() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse("$.login", validUsername);
    }

    @Test
    public void testGetValidUserHasPublicRepos() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse("$.public_repos");
    }

    @Test
    public void testGetValidUserTypeIsUser() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse("$.type", "User");
    }

    @Test
    public void testGetInvalidUserReturns404() {
        GetUserApi api = new GetUserApi(invalidUsername);
        api.expectResponseStatus(com.zebrunner.carina.api.http.HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}