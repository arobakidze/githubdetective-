package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${base_url}/rate_limit", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api_templates/get_rate_limit.json")
public class GetRateLimitApi extends AbstractApiMethodV2 {

    public GetRateLimitApi() {
        replaceUrlPlaceholder("base_url", System.getProperty("github.base.url", "https://api.github.com"));
        setHeaders("Authorization=Bearer " + System.getProperty("github.token", ""));
    }
}