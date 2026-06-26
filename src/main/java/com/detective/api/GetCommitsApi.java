package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/repos/${owner}/${repo}/commits", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api_templates/get_commits.json")
public class GetCommitsApi extends AbstractApiMethodV2 {

    public GetCommitsApi(String owner, String repo) {
        replaceUrlPlaceholder("base_url", System.getProperty("github.base.url", "https://api.github.com"));
        replaceUrlPlaceholder("owner", owner);
        replaceUrlPlaceholder("repo", repo);
        setHeaders("Authorization=Bearer " + System.getProperty("github.token", ""));
    }
}