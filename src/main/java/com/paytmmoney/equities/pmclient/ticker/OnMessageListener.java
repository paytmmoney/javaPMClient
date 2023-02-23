package com.paytmmoney.equities.pmclient.ticker;

import com.paytmmoney.equities.pmclient.model.Tick;

import java.util.ArrayList;

/**
 * Callback to listen to websocket on message arrival event.
 */
public interface OnMessageListener {
    void onMessage(ArrayList<Tick> response);
}
