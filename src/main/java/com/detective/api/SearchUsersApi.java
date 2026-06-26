package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/search/users?q=${query}", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api_templates/search_users.json")
public class SearchUsersApi extends AbstractApiMethodV2 {

    public SearchUsersApi(String query) {
        replaceUrlPlaceholder("base_url", System.getProperty("github.base.url", "https://api.github.com"));
        replaceUrlPlaceholder("query", query);
        setHeaders("Authorization=Bearer " + System.getProperty("github.token", ""));
    }
}