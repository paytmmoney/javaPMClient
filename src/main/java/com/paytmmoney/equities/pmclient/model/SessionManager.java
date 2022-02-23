package com.paytmmoney.equities.pmclient.model;

import org.apache.logging.log4j.util.Strings;

public class SessionManager {
    private String apiKey = null;
    private String apiSecretKey = null;
    private String accessToken = null;
    private boolean sessionAlive = false;

    public SessionManager(String apiKey, String apiSecretKey) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
    }

    public SessionManager(String apiKey, String apiSecretKey, String accessToken) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.accessToken = accessToken;
        setSessionAlive(!Strings.isBlank(accessToken));
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecretKey() {
        return apiSecretKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        setSessionAlive(!Strings.isBlank(accessToken));
    }

    public boolean isSessionExpired() {
        return !sessionAlive;
    }

    public void setSessionAlive(boolean sessionAlive) {
        this.sessionAlive = sessionAlive;
    }
}
