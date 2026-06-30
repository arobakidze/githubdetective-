package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.PropertiesPath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/users/${username}/repos", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/user_repos/_get/rs.json")
@PropertiesPath(path = "api/github/github.properties")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetUserReposApi extends AbstractApiMethodV2 {

    public GetUserReposApi(String username) {
        replaceUrlPlaceholder("base_url", GitHubConfig.get("api_url"));
        replaceUrlPlaceholder("username", username);
        addProperty("username", username);
        setHeaders("Authorization=Bearer " + GitHubConfig.get("github_token"));
    }
}
