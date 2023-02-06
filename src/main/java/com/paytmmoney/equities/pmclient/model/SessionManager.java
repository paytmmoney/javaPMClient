package com.paytmmoney.equities.pmclient.model;

public class SessionManager {
    private String apiKey = null;
    private String apiSecretKey = null;
    private String accessToken = null;
    private String publicAccessToken = null;
    private String readAccessToken = null;
    private boolean sessionAlive = false;

    public SessionManager(String apiKey, String apiSecretKey) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
    }

    public SessionManager(String apiKey, String apiSecretKey, String accessToken, String publicAccessToken, String readAccessToken) {
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;
        this.accessToken = accessToken;
        this.publicAccessToken = publicAccessToken;
        this.readAccessToken = readAccessToken;
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
    public String getPublicAccessToken() {
        return publicAccessToken;
    }
    public String getReadAccessToken() {
        return readAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setPublicAccessToken(String publicAccessToken) {
        this.publicAccessToken = publicAccessToken;
    }

    public void setReadAccessToken(String readAccessToken) {
        this.readAccessToken = readAccessToken;
    }
}
