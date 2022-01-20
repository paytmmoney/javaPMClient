package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.response.AccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionManagerServiceImplTest {

    MockedConstruction<RestTemplate> mocked;
    RestTemplate restTemplate;
    SessionManager sessionManager;
    SessionManagerServiceImpl sessionManagerServiceImpl;

    @Before
    public void setUp() {
        mocked = Mockito.mockConstruction(RestTemplate.class);
        sessionManagerServiceImpl = new SessionManagerServiceImpl();
        restTemplate = mocked.constructed().get(0);
        sessionManager = new SessionManager("1", "1", "1");
    }

    @After
    public void initMock() {
        mocked.close();
    }

    @Test
    public void testGenerateSession() throws Exception {
        AccessToken accessToken = new AccessToken("data");
        ResponseEntity<AccessToken> response = new ResponseEntity<AccessToken>(accessToken, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<AccessToken>>any())
        ).thenReturn(response);
        String result = sessionManagerServiceImpl.generateSession(sessionManager, "requestToken");
        Assert.assertEquals(result, "200 OK Session Generated Successfully for given request token requestToken");
    }

    @Test(expected = ApplicationException.class)
    public void testGenerateSessionException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<AccessToken>>any())
        ).thenReturn(null);
        sessionManagerServiceImpl.generateSession(sessionManager,"requestToken");
    }

    @Test
    public void testLogout() throws Exception {
        ResponseEntity<String> response = new ResponseEntity<String>("a,b,c", HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any())
        ).thenReturn(response);
        String result = sessionManagerServiceImpl.logout(sessionManager);
        Assert.assertEquals(result, "200 OK Session Expired");
    }

    @Test(expected = ApplicationException.class)
    public void testLogoutException() throws Exception {
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any())
        ).thenReturn(null);
        sessionManagerServiceImpl.logout(sessionManager);
    }
}
