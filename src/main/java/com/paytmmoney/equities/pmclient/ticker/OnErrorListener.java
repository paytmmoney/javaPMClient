package com.paytmmoney.equities.pmclient.ticker;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;

/**
 * Callback to listen to websocket onError event.
 */
public interface OnErrorListener {
    void onError(String errorMessage);

    void onError(Exception e);

    void onError(ApplicationException applicationException);
}
