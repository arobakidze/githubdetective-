package com.detective.api;

import com.zebrunner.carina.utils.R;

public final class GitHubConfig {

    private GitHubConfig() {
    }

    public static String get(String key) {
        String env = R.CONFIG.get("env");
        return R.CONFIG.get(env + "." + key);
    }
}
