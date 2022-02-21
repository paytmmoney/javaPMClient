package com.paytmmoney.equities.pmclient.model;

import org.apache.logging.log4j.util.Strings;

public class SessionManager {
    private String apiKey = null;
    private String apiSecretKey = null;
    private String stateKey = null;
    private String accessToken = null;
    private boolean sessionAlive = false;

    public SessionManager(String apiKey, String apiSecretKey, String stateKey) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.stateKey = stateKey;
    }

    public SessionManager(String apiKey, String apiSecretKey, String stateKey, String accessToken) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.stateKey = stateKey;
        this.accessToken = accessToken;
        setSessionAlive(!Strings.isBlank(accessToken));
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecretKey() {
        return apiSecretKey;
    }

    public String getStateKey() { return stateKey; }

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
