package com.detective.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Endpoint(url = "${base_url}/search/users?q=${query}", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api_templates/search_users.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class SearchUsersApi extends AbstractApiMethodV2 {

    public SearchUsersApi(String query) {
        Properties props = loadProps();

        replaceUrlPlaceholder("base_url", props.getProperty("github.base.url"));
        replaceUrlPlaceholder("query", query);

        setHeaders("Authorization=Bearer " + props.getProperty("github.token"));
    }

    private Properties loadProps() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
        return props;
    }
}