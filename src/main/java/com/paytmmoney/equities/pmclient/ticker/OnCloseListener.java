package com.paytmmoney.equities.pmclient.ticker;

/**
 * Callback to listen to websocket onClose event.
 */
public interface OnCloseListener {
    void onClose(String closeReason);
}
