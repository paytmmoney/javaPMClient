package com.paytmmoney.equities.pmclient.service;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;

public interface SessionManagerService {

    String generateSession(SessionManager sessionManager, String requestToken) throws ApplicationException;

    String logout(SessionManager sessionManager) throws ApplicationException;
}
