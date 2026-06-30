package com.detective.tests;

import com.detective.api.GetUserReposApi;
import com.detective.api.GitHubConfig;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserReposTest {

    private String validUsername;
    private String invalidUsername;

    @BeforeClass
    public void setUp() {
        validUsername = GitHubConfig.get("github_username");
        invalidUsername = "this_user_does_not_exist_xyz_123456789";
    }

    @Test
    public void testGetReposValidUserReturns200() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetReposResponseIsArray() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetReposEachHasName() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetReposEachHasOwner() {
        GetUserReposApi api = new GetUserReposApi(validUsername);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetReposInvalidUserReturns404() {
        GetUserReposApi api = new GetUserReposApi(invalidUsername);
        api.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}
