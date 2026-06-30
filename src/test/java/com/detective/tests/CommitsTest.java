package com.detective.tests;

import com.detective.api.GetCommitsApi;
import com.detective.api.GitHubConfig;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommitsTest {

    private String owner;
    private String repo;
    private String invalidRepo;

    @BeforeClass
    public void setUp() {
        owner = GitHubConfig.get("github_username");
        repo = GitHubConfig.get("github_repo");
        invalidRepo = "repo_that_does_not_exist_xyz";
    }

    @Test
    public void testGetCommitsValidRepoReturns200() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetCommitsResponseIsArray() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetCommitsHasAuthor() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetCommitsHasMessage() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.LENIENT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
    }

    @Test
    public void testGetCommitsInvalidRepoReturns404() {
        GetCommitsApi api = new GetCommitsApi(owner, invalidRepo);
        api.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}
