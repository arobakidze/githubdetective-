package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.PropertiesPath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/repos/${owner}/${repo}/commits", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/commits/_get/rs.json")
@PropertiesPath(path = "api/github/github.properties")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetCommitsApi extends AbstractApiMethodV2 {

    public GetCommitsApi(String owner, String repo) {
        replaceUrlPlaceholder("base_url", GitHubConfig.get("api_url"));
        replaceUrlPlaceholder("owner", owner);
        replaceUrlPlaceholder("repo", repo);
        addProperty("owner", owner);
        addProperty("repo", repo);
        setHeaders("Authorization=Bearer " + GitHubConfig.get("github_token"));
    }
}
