package com.detective.tests;

import com.detective.api.GetCommitsApi;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommitsTest {

    private String owner;
    private String repo;
    private String invalidRepo;

    @BeforeClass
    public void setUp() {
        owner = System.getProperty("github.username", "arobakidze");
        repo = System.getProperty("github.repo", "hotel-management");
        invalidRepo = "repo_that_does_not_exist_xyz";
    }

    @Test
    public void testGetCommitsValidRepoReturns200() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetCommitsResponseIsArray() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetCommitsHasAuthor() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetCommitsHasMessage() {
        GetCommitsApi api = new GetCommitsApi(owner, repo);
        api.callAPIExpectSuccess();
    }

    @Test
    public void testGetCommitsInvalidRepoReturns404() {
        GetCommitsApi api = new GetCommitsApi(owner, invalidRepo);
        api.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        api.callAPI();
    }
}