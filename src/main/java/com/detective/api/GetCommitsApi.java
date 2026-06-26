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

@Endpoint(url = "${base_url}/repos/${owner}/${repo}/commits", methodType = HttpMethodType.GET)
@RequestTemplatePath(path = "api_templates/get_commits.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetCommitsApi extends AbstractApiMethodV2 {

    public GetCommitsApi(String owner, String repo) {
        Properties props = loadProps();

        replaceUrlPlaceholder("base_url", props.getProperty("github.base.url"));
        replaceUrlPlaceholder("owner", owner);
        replaceUrlPlaceholder("repo", repo);

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