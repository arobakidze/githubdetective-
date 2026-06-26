package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/users/${username}/repos", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api_templates/get_user_repos.json")
public class GetUserReposApi extends AbstractApiMethodV2 {

    public GetUserReposApi(String username) {
        replaceUrlPlaceholder("base_url", System.getProperty("github.base.url", "https://api.github.com"));
        replaceUrlPlaceholder("username", username);
        setHeaders("Authorization=Bearer " + System.getProperty("github.token", ""));
    }
}