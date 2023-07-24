package com.paytmmoney.equities.pmclient.ticker;

import com.paytmmoney.equities.pmclient.constant.ByteConversionConstants;
import com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.PreferenceDto;
import com.paytmmoney.equities.pmclient.model.Tick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_BUY_ORDER_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_BUY_PRICE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_BUY_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_PACKETS_COUNT;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_SELL_ORDER_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_SELL_PRICE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_SELL_QUANTITY_OFFSET;

class WebSocketClientTest {
    @Mock
    OnMessageListener onMessageListener;
    @Mock
    OnOpenListener onOpenListener;
    @Mock
    OnCloseListener onCloseListener;
    @Mock
    OnErrorListener onErrorListener;
    @Mock
    Session ws;
    @Mock
    DecimalFormat decimalFormat;
    @Mock
    CloseReason.CloseCode closeCode;
    @InjectMocks
    WebSocketClient webSocketClient = new WebSocketClient("accessToken");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webSocketClient.setOnMessageListener(new OnMessageListener() {
            @Override
            public void onMessage(ArrayList<Tick> response) {
                // do nothing
            }
        });

        webSocketClient.setOnOpenListener(new OnOpenListener() {
            @Override
            public void onOpen() {
                // do nothing
            }
        });

        webSocketClient.setOnCloseListener(new OnCloseListener() {
            @Override
            public void onClose(String closeReason) {
                // do nothing
            }
        });

        webSocketClient.setOnErrorListener(new OnErrorListener() {
            @Override
            public void onError(String errorMessage) {
                // do nothing
            }

            @Override
            public void onError(Exception e) {
                // do nothing
            }

            @Override
            public void onError(ApplicationException applicationException) {
                // do nothing
            }
        });
    }

    @Test
    void testConnect() {
        webSocketClient.connect();
    }

    @Test
    void testSetOnMessageListener() {
        webSocketClient.setOnMessageListener(null);
    }

    @Test
    void testSetOnOpenListener() {
        webSocketClient.setOnOpenListener(null);
    }

    @Test
    void testSetOnCloseListener() {
        webSocketClient.setOnCloseListener(null);
    }

    @Test
    void testSetOnErrorListener() {
        webSocketClient.setOnErrorListener(null);
    }

    @Test
    void testOnOpen() {
        webSocketClient.onOpen(null);
    }

    @Test
    void testOnMessage() {
        ByteBuffer indexLtpPacket = ByteBuffer.allocate(ByteResponseCreationConstants.INDEX_LTP_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        indexLtpPacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.INDEX_LTP_PKT) //packet code
                .putFloat(ByteResponseCreationConstants.LTP_OFFSET, 100) //last_price
                .putInt(ByteResponseCreationConstants.INDEX_LTP_LUT_OFFSET, 1234) //last_traded_time
                .putInt(ByteResponseCreationConstants.INDEX_LTP_SECURITY_ID_OFFSET, 3456) //security_id
                .put(ByteResponseCreationConstants.INDEX_LTP_TRADABLE_OFFSET, (byte) 1) //tradable
                .put(ByteResponseCreationConstants.INDEX_LTP_MODETYPE_OFFSET, ByteConversionConstants.INDEX_LTP_PKT) //mode
                .putFloat(ByteResponseCreationConstants.INDEX_LTP_CHANGE_ABSOLUTE_OFFSET, 1) //change_absolute
                .putFloat(ByteResponseCreationConstants.INDEX_LTP_CHANGE_PERCENT_OFFSET, 1); //change_percent)
        webSocketClient.onMessage(indexLtpPacket);
    }

    @Test
    void testOnErrorMessage() {
        webSocketClient.onErrorMessage("errorMessage");
    }

    @Test
    void testOnClose() {
        webSocketClient.onClose(new CloseReason(closeCode, "bad request"));
    }

    @Test
    void testOnError() {
        webSocketClient.onError(new Throwable());
    }

    @Test
    void testSubscribe() {
        webSocketClient.subscribe(new ArrayList<>(Collections.singletonList(new PreferenceDto("actionType", "modeType", "scripType", "exchangeType", "scripId"))));
    }

    @Test
    void testParseBinaryIndexLtp() {
        ByteBuffer indexLtpPacket = ByteBuffer.allocate(ByteResponseCreationConstants.INDEX_LTP_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        indexLtpPacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.INDEX_LTP_PKT) //packet code
                .putFloat(ByteResponseCreationConstants.LTP_OFFSET, 100) //last_price
                .putInt(ByteResponseCreationConstants.INDEX_LTP_LUT_OFFSET, 1234) //last_traded_time
                .putInt(ByteResponseCreationConstants.INDEX_LTP_SECURITY_ID_OFFSET, 3456) //security_id
                .put(ByteResponseCreationConstants.INDEX_LTP_TRADABLE_OFFSET, (byte) 1) //tradable
                .put(ByteResponseCreationConstants.INDEX_LTP_MODETYPE_OFFSET, ByteConversionConstants.INDEX_LTP_PKT) //mode
                .putFloat(ByteResponseCreationConstants.INDEX_LTP_CHANGE_ABSOLUTE_OFFSET, 1) //change_absolute
                .putFloat(ByteResponseCreationConstants.INDEX_LTP_CHANGE_PERCENT_OFFSET, 1); //change_percent)
        ArrayList<Tick> result = webSocketClient.parseBinary(indexLtpPacket);
    }

    @Test
    void testParseBinaryIndexQuote() {
        ByteBuffer indexQuotePacket = ByteBuffer.allocate(ByteResponseCreationConstants.INDEX_QUOTE_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        indexQuotePacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.INDEX_QUOTE_PKT)
                .putFloat(ByteResponseCreationConstants.LTP_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.INDEX_QUOTE_SECURITY_ID_OFFSET, 3456)
                .put(ByteResponseCreationConstants.INDEX_QUOTE_TRADABLE_OFFSET, (byte) 1)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_OPEN_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_CLOSE_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_HIGH_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_LOW_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_CHANGE_PERCENT_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_CHANGE_ABSOLUTE_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_52_WEEK_HIGH_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_QUOTE_52_WEEK_HIGH_OFFSET, 100);
        ArrayList<Tick> result = webSocketClient.parseBinary(indexQuotePacket);
    }

    @Test
    void testParseBinaryIndexFull() {
        ByteBuffer indexFullPacket = ByteBuffer.allocate(ByteResponseCreationConstants.INDEX_FULL_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        indexFullPacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.INDEX_FULL_PKT)
                .putFloat(ByteResponseCreationConstants.LTP_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.INDEX_FULL_SECURITY_ID_OFFSET, 3456)
                .put(ByteResponseCreationConstants.INDEX_FULL_TRADABLE_OFFSET, (byte) 1)
                .put(ByteResponseCreationConstants.INDEX_FULL_MODETYPE_OFFSET, (byte) 1)
                .putFloat(ByteResponseCreationConstants.INDEX_FULL_OPEN_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_FULL_CLOSE_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_FULL_HIGH_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_FULL_LOW_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_FULL_CHANGE_PERCENT_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.INDEX_FULL_CHANGE_ABSOLUTE_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.INDEX_FULL_LUT_OFFSET, 100);
        ArrayList<Tick> result = webSocketClient.parseBinary(indexFullPacket);
    }

    @Test
    void testParseBinaryLtp() {
        ByteBuffer ltpPacket = ByteBuffer.allocate(ByteResponseCreationConstants.LTP_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        ltpPacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.LTP_PKT)
                .putFloat(ByteResponseCreationConstants.LTP_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.LTP_LTT_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.LTP_SECURITY_ID_OFFSET, 3456)
                .put(ByteResponseCreationConstants.LTP_TRADABLE_OFFSET, (byte) 1)
                .put(ByteResponseCreationConstants.LTP_MODETYPE_OFFSET, (byte) 1)
                .putFloat(ByteResponseCreationConstants.LTP_CHANGE_ABSOLUTE_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.LTP_CHANGE_PERCENT_OFFSET, 100);
        ArrayList<Tick> result = webSocketClient.parseBinary(ltpPacket);
    }

    @Test
    void testParseBinaryQuote() {
        ByteBuffer quotePacket = ByteBuffer.allocate(ByteResponseCreationConstants.QUOTE_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        quotePacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.QUOTE_PKT)
                .putFloat(ByteResponseCreationConstants.LTP_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.QUOTE_LTT_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.QUOTE_SECURITY_ID_OFFSET, 3456)
                .put(ByteResponseCreationConstants.QUOTE_TRADABLE_OFFSET, (byte) 1)
                .put(ByteResponseCreationConstants.QUOTE_MODETYPE_OFFSET, (byte) 1)
                .putInt(ByteResponseCreationConstants.QUOTE_LAST_TRADED_QUANTITY_OFFSET, 1)
                .putFloat(ByteResponseCreationConstants.QUOTE_AVG_TRADED_PRICE_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.QUOTE_VOLUME_OFFSET, 10)
                .putInt(ByteResponseCreationConstants.QUOTE_TOTAL_BUY_QUANTITY_OFFSET, 10)
                .putInt(ByteResponseCreationConstants.QUOTE_TOTAL_SELL_QUANTITY_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.QUOTE_OPEN_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.QUOTE_CLOSE_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.QUOTE_HIGH_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.QUOTE_LOW_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.QUOTE_CHANGE_PERCENT_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.QUOTE_CHANGE_ABSOLUTE_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.QUOTE_52_WEEK_HIGH_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.QUOTE_52_WEEK_LOW_OFFSET, 10);
        ArrayList<Tick> result = webSocketClient.parseBinary(quotePacket);
    }

    @Test
    void testParseBinaryFull() {
        ByteBuffer fullPacket = ByteBuffer.allocate(ByteResponseCreationConstants.FULL_PACKET_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < DEPTH_PACKETS_COUNT; i++) {
            fullPacket.putInt(DEPTH_BUY_QUANTITY_OFFSET + (i * DEPTH_PACKET_SIZE), 123)
                    .putInt(DEPTH_SELL_QUANTITY_OFFSET + (i * DEPTH_PACKET_SIZE), 123)
                    .putShort(DEPTH_BUY_ORDER_OFFSET + (i * DEPTH_PACKET_SIZE), (short) 1)
                    .putShort(DEPTH_SELL_ORDER_OFFSET + (i * DEPTH_PACKET_SIZE), (short) 1)
                    .putFloat(DEPTH_BUY_PRICE_OFFSET + (i * DEPTH_PACKET_SIZE), 123)
                    .putFloat(DEPTH_SELL_PRICE_OFFSET + (i * DEPTH_PACKET_SIZE), 123);
        }

        fullPacket.put(ByteResponseCreationConstants.TYPE_OFFSET, ByteConversionConstants.FULL_PKT)
                .putFloat(ByteResponseCreationConstants.FULL_LTP_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.FULL_LTT_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.FULL_SECURITY_ID_OFFSET, 3456)
                .put(ByteResponseCreationConstants.FULL_TRADABLE_OFFSET, (byte) 1)
                .put(ByteResponseCreationConstants.FULL_MODETYPE_OFFSET, (byte) 1)
                .putInt(ByteResponseCreationConstants.FULL_LAST_TRADED_QUANTITY_OFFSET, 1)
                .putFloat(ByteResponseCreationConstants.FULL_AVG_TRADED_PRICE_OFFSET, 100)
                .putInt(ByteResponseCreationConstants.FULL_VOLUME_OFFSET, 10)
                .putInt(ByteResponseCreationConstants.FULL_TOTAL_BUY_QUANTITY_OFFSET, 10)
                .putInt(ByteResponseCreationConstants.FULL_TOTAL_SELL_QUANTITY_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.FULL_OPEN_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.FULL_CLOSE_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.FULL_HIGH_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.FULL_LOW_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.FULL_CHANGE_PERCENT_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.FULL_CHANGE_ABSOLUTE_OFFSET, 100)
                .putFloat(ByteResponseCreationConstants.FULL_52_WEEK_HIGH_OFFSET, 10)
                .putFloat(ByteResponseCreationConstants.FULL_52_WEEK_LOW_OFFSET, 10)
                .putInt(ByteResponseCreationConstants.FULL_OI_OFFSET, 1)
                .putInt(ByteResponseCreationConstants.FULL_CHANGE_OI_OFFSET, 1);
        ArrayList<Tick> result = webSocketClient.parseBinary(fullPacket);
    }
}