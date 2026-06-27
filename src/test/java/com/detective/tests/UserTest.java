package com.detective.tests;

import com.detective.api.GetUserApi;
import com.detective.api.GitHubConfig;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    private String validUsername;
    private String invalidUsername;

    @BeforeClass
    public void setUp() {
        validUsername = GitHubConfig.get("github_username");
        invalidUsername = "this_user_does_not_exist_xyz_123456789";
    }

    @Test
    public void testGetValidUserReturns200() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetValidUserHasLogin() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetValidUserHasPublicRepos() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetValidUserTypeIsUser() {
        GetUserApi api = new GetUserApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT);
    }

    @Test
    public void testGetInvalidUserReturns404() {
        GetUserApi api = new GetUserApi(invalidUsername);
        api.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}
