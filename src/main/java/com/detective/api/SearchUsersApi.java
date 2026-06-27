package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.PropertiesPath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "${base_url}/search/users?q=${query}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/github/search_users/_get/rs.json")
@PropertiesPath(path = "api/github/github.properties")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class SearchUsersApi extends AbstractApiMethodV2 {

    public SearchUsersApi(String query) {
        replaceUrlPlaceholder("base_url", GitHubConfig.get("api_url"));
        replaceUrlPlaceholder("query", query);
        addProperty("query", query);
        setHeaders("Authorization=Bearer " + GitHubConfig.get("github_token"));
    }
}
