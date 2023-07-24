package com.paytmmoney.equities.pmclient.ticker;


import com.google.gson.Gson;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.DepthPacket;
import com.paytmmoney.equities.pmclient.model.PreferenceDto;
import com.paytmmoney.equities.pmclient.model.Tick;
import com.paytmmoney.equities.pmclient.util.EpochConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.glassfish.tyrus.core.HandshakeException;
import org.springframework.http.HttpStatus;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.net.ConnectException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.paytmmoney.equities.pmclient.constant.ApplicationConstants.DEFAULT_MAX_RECONNECT_ATTEMPT;
import static com.paytmmoney.equities.pmclient.constant.ApplicationConstants.INITIAL_RECONNECT_DELAY;
import static com.paytmmoney.equities.pmclient.constant.ByteConversionConstants.FULL_PKT;
import static com.paytmmoney.equities.pmclient.constant.ByteConversionConstants.INDEX_FULL_PKT;
import static com.paytmmoney.equities.pmclient.constant.ByteConversionConstants.INDEX_LTP_PKT;
import static com.paytmmoney.equities.pmclient.constant.ByteConversionConstants.INDEX_QUOTE_PKT;
import static com.paytmmoney.equities.pmclient.constant.ByteConversionConstants.LTP_PKT;
import static com.paytmmoney.equities.pmclient.constant.ByteConversionConstants.QUOTE_PKT;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_BUY_ORDER_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_BUY_PRICE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_BUY_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_PACKETS_COUNT;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_SELL_ORDER_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_SELL_PRICE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.DEPTH_SELL_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_52_WEEK_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_52_WEEK_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_AVG_TRADED_PRICE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_CHANGE_ABSOLUTE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_CHANGE_OI_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_CHANGE_PERCENT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_CLOSE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_LAST_TRADED_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_LTP_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_LTT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_MODETYPE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_OI_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_OPEN_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_SECURITY_ID_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_TOTAL_BUY_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_TOTAL_SELL_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_TRADABLE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.FULL_VOLUME_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_CHANGE_ABSOLUTE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_CHANGE_PERCENT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_CLOSE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_LUT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_MODETYPE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_OPEN_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_SECURITY_ID_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_FULL_TRADABLE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_CHANGE_ABSOLUTE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_CHANGE_PERCENT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_LUT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_MODETYPE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_SECURITY_ID_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_LTP_TRADABLE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_52_WEEK_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_52_WEEK_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_CHANGE_ABSOLUTE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_CHANGE_PERCENT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_CLOSE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_MODETYPE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_OPEN_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_SECURITY_ID_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.INDEX_QUOTE_TRADABLE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_CHANGE_ABSOLUTE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_CHANGE_PERCENT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_LTT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_MODETYPE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_SECURITY_ID_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.LTP_TRADABLE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_52_WEEK_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_52_WEEK_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_AVG_TRADED_PRICE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_CHANGE_ABSOLUTE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_CHANGE_PERCENT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_CLOSE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_HIGH_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_LAST_TRADED_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_LOW_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_LTT_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_MODETYPE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_OPEN_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_PACKET_SIZE;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_SECURITY_ID_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_TOTAL_BUY_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_TOTAL_SELL_QUANTITY_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_TRADABLE_OFFSET;
import static com.paytmmoney.equities.pmclient.constant.ByteResponseCreationConstants.QUOTE_VOLUME_OFFSET;

@ClientEndpoint
@Slf4j
/**
 *  sends preferences to Broadcast server and get data packets (in ByteBuffer format) from the server. This Class
 *  converts the packets received from server into readable format which includes our own business logic.
 */
public class WebSocketClient {

    private String wsUrl; // url to which request is hit for creating websocket connection
    private OnMessageListener onMessageListener;
    private OnOpenListener onOpenListener;
    private OnCloseListener onCloseListener;
    private OnErrorListener onErrorListener;
    private Session ws; // session object required to manage that session
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00"); // for rounding off floating point value to 2 decimal places
    private int reconnectCount = NumberUtils.INTEGER_ZERO;
    private Long reconnectDelay = INITIAL_RECONNECT_DELAY;
    private boolean doReconnect = false;
    private int maxReconnectAttempt = DEFAULT_MAX_RECONNECT_ATTEMPT;

    /**
     * Initialize WebSocketClient.
     *
     * @param accessToken is the public access token received after successful login process.
     */
    public WebSocketClient(String accessToken) {
        createUrl(accessToken);
    }

    /**
     * Initialize WebSocketClient and sets doReconnect and maxReconnectAttempt variables..
     *
     * @param accessToken         is the public access token received after successful login process.
     * @param doReconnect         boolean value determining whether reconnect feature is allowed or not.
     * @param maxReconnectAttempt the no. of retries made to server to create connection.
     */
    public WebSocketClient(String accessToken, boolean doReconnect, int maxReconnectAttempt) {
        createUrl(accessToken);
        this.doReconnect = doReconnect;
        this.maxReconnectAttempt = maxReconnectAttempt;
    }

    /**
     * Create a websocket connection with Broadcast server
     */
    public void connect() {
        try {
            ContainerProvider.getWebSocketContainer().connectToServer(this, URI.create(wsUrl));
        } catch (Exception e) {
            if (onErrorListener != null) {
                onErrorListener.onError(e);
                if (doReconnect && is5xxServerError(e)) {
                    reconnect();
                }
            }
        }
    }

    /**
     * Creates url for websocket connection.
     *
     * @param accessToken public access token provided by user
     */
    private void createUrl(String accessToken) {
        wsUrl = ApiConstants.WEBSOCKET_URL + accessToken;
    }

    /**
     * Set listener for listening to incoming data packets.
     *
     * @param listener listens for each packet received
     */
    public void setOnMessageListener(OnMessageListener listener) {
        onMessageListener = listener;
    }

    /**
     * Set listener for on connection open.
     *
     * @param listener is used to listen to onOpen event.
     */
    public void setOnOpenListener(OnOpenListener listener) {
        onOpenListener = listener;
    }

    /**
     * Set listener for on connection is disconnected.
     *
     * @param listener is used to listen to onClose event.
     */
    public void setOnCloseListener(OnCloseListener listener) {
        onCloseListener = listener;
    }

    /**
     * Set error listener.
     *
     * @param listener of type OnError which listens to all the type of errors that may arise in WebSocketClient class.
     */
    public void setOnErrorListener(OnErrorListener listener) {
        onErrorListener = listener;
    }

    /**
     * This method is invoked when websocket connection opens
     *
     * @param session is used to manage that connection
     */
    @OnOpen
    public void onOpen(Session session) {
        ws = session;
        resetReconnectCount();
        if (onOpenListener != null)
            onOpenListener.onOpen();
    }

    /**
     * This method is invoked when open connection sends messages to our client
     *
     * @param packet response in ByteBuffer format sent by server
     */
    @OnMessage
    public void onMessage(ByteBuffer packet) {
        if (onMessageListener != null)
            onMessageListener.onMessage(parseBinary(packet));
    }

    /**
     * This method is invoked when open connection sends error messages to our client
     *
     * @param errorMessage error message sent by server
     */
    @OnMessage
    public void onErrorMessage(String errorMessage) {
        if (onErrorListener != null)
            onErrorListener.onError(errorMessage);
    }

    /**
     * This method is invoked when websocket connection closes
     *
     * @param reason reason of connection closure
     */
    @OnClose
    public void onClose(CloseReason reason) {
        if (onCloseListener != null) {
            onCloseListener.onClose(reason.toString());
            if (doReconnect && (reason.getCloseCode() != CloseReason.CloseCodes.NORMAL_CLOSURE)) {
                reconnect();
            }
        }
    }

    /**
     * This method invoked when any error occurs in websocket connection
     *
     * @param throwable contains error details
     */
    @OnError
    public void onError(Throwable throwable) {
        if (onErrorListener != null) {
            onErrorListener.onError(throwable.getMessage());
            Throwable cause = throwable.getCause();
            if (doReconnect && is5xxServerError(throwable)) {
                reconnect();
            }
        }
    }

    /**
     * This method tries to reconnect to server for maxReconnectAttempt times.
     */
    /**
     * This method tries to reconnect to server for maxReconnectAttempt times.
     */
    private void reconnect() {
        try {
            Thread.sleep(reconnectDelay);
            reconnectDelay *= NumberUtils.INTEGER_TWO;
            reconnectCount += NumberUtils.INTEGER_ONE;
            if (reconnectCount <= maxReconnectAttempt) {
                connect();
            }
            if (reconnectCount > maxReconnectAttempt) {
                resetReconnectCount();
            }
        } catch (Exception e) {
            if (onErrorListener != null) {
                onErrorListener.onError(e);
            }
        }
    }

    private boolean is5xxServerError(Throwable e) {
        int httpStatusCode = 400;
        if (e.getCause() instanceof HandshakeException) {
            HandshakeException handshakeException = (HandshakeException) e.getCause();
            httpStatusCode = handshakeException.getHttpStatusCode();
        }
        if (e instanceof ConnectException) {
            httpStatusCode = 500;
        }
        return HttpStatus.valueOf(httpStatusCode).is5xxServerError();
    }

    private void resetReconnectCount() {
        reconnectDelay = INITIAL_RECONNECT_DELAY;
        reconnectCount = NumberUtils.INTEGER_ZERO;
    }

    /**
     * This method is used to explicitly close connection with the server.
     */
    public void closeConnection() {
        try {
            if (ws != null) {
                ws.close();
            }
        } catch (Exception e) {
            if (onErrorListener != null) {
                onErrorListener.onError(e);
            }
        }
    }

    /**
     * Subscribes for list of preferences.
     *
     * @param preferences is list of preferences to be subscribed for.
     */
    public void subscribe(ArrayList<PreferenceDto> preferences) {
        if (ws != null) {
            if (ws.isOpen()) {
                Gson gson = new Gson();
                String pref = gson.toJson(preferences);
                ws.getAsyncRemote().sendText(pref);
            } else {
                if (onErrorListener != null) {
                    onErrorListener.onError(new ApplicationException("ticker is not connected", 504));
                }
            }
        } else {
            if (onErrorListener != null) {
                onErrorListener.onError(new ApplicationException("ticker is null not connected", 504));
            }
        }
    }

    /**
     * This method parses binary data received from Broadcast server to get ticks for each preference subscribed.
     *
     * @param bufferPackets ByteBuffer packets received from Broadcast Server
     * @return List of parsed responses.
     */
    public ArrayList<Tick> parseBinary(ByteBuffer bufferPackets) {
        ArrayList<Tick> ticks = new ArrayList<>();
        ByteBuffer packet = ByteBuffer.allocate(bufferPackets.capacity()).order(ByteOrder.LITTLE_ENDIAN);
        bufferPackets.position(NumberUtils.INTEGER_ZERO);
        packet.put(bufferPackets);
        int position = 0, bufferLength = packet.capacity();
        while (position < bufferLength) {
            byte packetType = packet.get(position);
            switch (packetType) {
                case INDEX_LTP_PKT:
                    processIndexLtpPacket(ticks, packet, position);
                    position += INDEX_LTP_PACKET_SIZE;
                    break;
                case INDEX_QUOTE_PKT:
                    processIndexQuotePacket(ticks, packet, position);
                    position += INDEX_QUOTE_PACKET_SIZE;
                    break;
                case INDEX_FULL_PKT:
                    processIndexFullPacket(ticks, packet, position);
                    position += INDEX_FULL_PACKET_SIZE;
                    break;
                case LTP_PKT:
                    processLtpPacket(ticks, packet, position);
                    position += LTP_PACKET_SIZE;
                    break;
                case QUOTE_PKT:
                    processQuotePacket(ticks, packet, position);
                    position += QUOTE_PACKET_SIZE;
                    break;
                case FULL_PKT:
                    processFullPacket(ticks, packet, position);
                    position += FULL_PACKET_SIZE;
                    break;
            }
        }
        return ticks;
    }

    /**
     * parses indexLtp packets into readable format
     *
     * @param ticks    Response Array in readable format
     * @param packet   ByteBuffer packet received from server
     * @param position current position of packet to be processed
     */
    private void processIndexLtpPacket(ArrayList<Tick> ticks, ByteBuffer packet, int position) {
        Tick tick = Tick.builder()
                .lastTradedPrice(packet.getFloat(position + LTP_OFFSET))
                .lastUpdatedTime(EpochConverterUtil.epochConverter(packet.getInt(position + INDEX_LTP_LUT_OFFSET)))
                .securityId(packet.getInt(position + INDEX_LTP_SECURITY_ID_OFFSET))
                .tradable(packet.get(position + INDEX_LTP_TRADABLE_OFFSET))
                .mode(packet.get(position + INDEX_LTP_MODETYPE_OFFSET))
                .changeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + INDEX_LTP_CHANGE_ABSOLUTE_OFFSET))))
                .changePercent(packet.getFloat(position + INDEX_LTP_CHANGE_PERCENT_OFFSET))
                .build();
        ticks.add(tick);
    }

    /**
     * parses indexQuote packets into readable format
     *
     * @param ticks    Response Array in readable format
     * @param packet   ByteBuffer packet received from server
     * @param position current position of packet to be processed
     */
    private void processIndexQuotePacket(ArrayList<Tick> ticks, ByteBuffer packet, int position) {
        Tick tick = Tick.builder()
                .lastTradedPrice(packet.getFloat(position + LTP_OFFSET))
                .securityId(packet.getInt(position + INDEX_QUOTE_SECURITY_ID_OFFSET))
                .tradable(packet.get(position + INDEX_QUOTE_TRADABLE_OFFSET))
                .mode(packet.get(position + INDEX_QUOTE_MODETYPE_OFFSET))
                .open(packet.getFloat(position + INDEX_QUOTE_OPEN_OFFSET))
                .close(packet.getFloat(position + INDEX_QUOTE_CLOSE_OFFSET))
                .high(packet.getFloat(position + INDEX_QUOTE_HIGH_OFFSET))
                .low(packet.getFloat(position + INDEX_QUOTE_LOW_OFFSET))
                .changePercent(packet.getFloat(position + INDEX_QUOTE_CHANGE_PERCENT_OFFSET))
                .changeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + INDEX_QUOTE_CHANGE_ABSOLUTE_OFFSET))))
                .fiftyTwoWeekHigh(packet.getFloat(position + INDEX_QUOTE_52_WEEK_HIGH_OFFSET))
                .fiftyTwoWeekLow(packet.getFloat(position + INDEX_QUOTE_52_WEEK_LOW_OFFSET))
                .build();
        ticks.add(tick);
    }

    /**
     * parses indexFull packets into readable format
     *
     * @param ticks    Response Array in readable format
     * @param packet   ByteBuffer packet received from server
     * @param position current position of packet to be processed
     */
    private void processIndexFullPacket(ArrayList<Tick> ticks, ByteBuffer packet, int position) {
        Tick tick = Tick.builder()
                .lastTradedPrice(packet.getFloat(position + LTP_OFFSET))
                .securityId(packet.getInt(position + INDEX_FULL_SECURITY_ID_OFFSET))
                .tradable(packet.get(position + INDEX_FULL_TRADABLE_OFFSET))
                .mode(packet.get(position + INDEX_FULL_MODETYPE_OFFSET))
                .open(packet.getFloat(position + INDEX_FULL_OPEN_OFFSET))
                .close(packet.getFloat(position + INDEX_FULL_CLOSE_OFFSET))
                .high(packet.getFloat(position + INDEX_FULL_HIGH_OFFSET))
                .low(packet.getFloat(position + INDEX_FULL_LOW_OFFSET))
                .changePercent(packet.getFloat(position + INDEX_FULL_CHANGE_PERCENT_OFFSET))
                .changeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + INDEX_FULL_CHANGE_ABSOLUTE_OFFSET))))
                .lastUpdatedTime(EpochConverterUtil.epochConverter(packet.getInt(position + INDEX_FULL_LUT_OFFSET)))
                .build();
        ticks.add(tick);
    }

    /**
     * parses ltp packets into readable format
     *
     * @param ticks    Response Array in readable format
     * @param packet   ByteBuffer packet received from server
     * @param position current position of packet to be processed
     */
    private void processLtpPacket(ArrayList<Tick> ticks, ByteBuffer packet, int position) {
        Tick tick = Tick.builder()
                .lastTradedPrice(packet.getFloat(position + LTP_OFFSET))
                .lastTradedTime(EpochConverterUtil.epochConverter(packet.getInt(position + LTP_LTT_OFFSET)))
                .securityId(packet.getInt(position + LTP_SECURITY_ID_OFFSET))
                .tradable(packet.get(position + LTP_TRADABLE_OFFSET))
                .mode(packet.get(position + LTP_MODETYPE_OFFSET))
                .changeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + LTP_CHANGE_ABSOLUTE_OFFSET))))
                .changePercent(packet.getFloat(position + LTP_CHANGE_PERCENT_OFFSET))
                .build();
        ticks.add(tick);
    }

    /**
     * parses quote packets into readable format
     *
     * @param ticks    Response Array in readable format
     * @param packet   ByteBuffer packet received from server
     * @param position current position of packet to be processed
     */
    private void processQuotePacket(ArrayList<Tick> ticks, ByteBuffer packet, int position) {
        Tick tick = Tick.builder()
                .lastTradedPrice(packet.getFloat(position + LTP_OFFSET))
                .lastTradedTime(EpochConverterUtil.epochConverter(packet.getInt(position + QUOTE_LTT_OFFSET)))
                .securityId(packet.getInt(position + QUOTE_SECURITY_ID_OFFSET))
                .tradable(packet.get(position + QUOTE_TRADABLE_OFFSET))
                .mode(packet.get(position + QUOTE_MODETYPE_OFFSET))
                .lastTradedQuantity(packet.getInt(position + QUOTE_LAST_TRADED_QUANTITY_OFFSET))
                .averageTradedPrice(packet.getFloat(position + QUOTE_AVG_TRADED_PRICE_OFFSET))
                .volumeTraded(packet.getInt(position + QUOTE_VOLUME_OFFSET))
                .totalBuyQuantity(packet.getInt(position + QUOTE_TOTAL_BUY_QUANTITY_OFFSET))
                .totalSellQuantity(packet.getInt(position + QUOTE_TOTAL_SELL_QUANTITY_OFFSET))
                .open(packet.getFloat(position + QUOTE_OPEN_OFFSET))
                .close(packet.getFloat(position + QUOTE_CLOSE_OFFSET))
                .high(packet.getFloat(position + QUOTE_HIGH_OFFSET))
                .low(packet.getFloat(position + QUOTE_LOW_OFFSET))
                .changePercent(packet.getFloat(position + QUOTE_CHANGE_PERCENT_OFFSET))
                .changeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + QUOTE_CHANGE_ABSOLUTE_OFFSET))))
                .fiftyTwoWeekHigh(packet.getFloat(position + QUOTE_52_WEEK_HIGH_OFFSET))
                .fiftyTwoWeekLow(packet.getFloat(position + QUOTE_52_WEEK_LOW_OFFSET))
                .build();
        ticks.add(tick);
    }

    /**
     * parses full packets into readable format
     *
     * @param ticks    Response Array in readable format
     * @param packet   ByteBuffer packet received from server
     * @param position current position of packet to be processed
     */
    private void processFullPacket(ArrayList<Tick> ticks, ByteBuffer packet, int position) {
        ArrayList<DepthPacket> depthPacketList = new ArrayList<>();
        for (int i = 0; i < DEPTH_PACKETS_COUNT; i++) {
            DepthPacket depthPacket = DepthPacket.builder()
                    .packetNo(i + 1)
                    .buyQuantity(packet.getInt(position + DEPTH_BUY_QUANTITY_OFFSET + (i * DEPTH_PACKET_SIZE)))
                    .sellQuantity(packet.getInt(position + DEPTH_SELL_QUANTITY_OFFSET + (i * DEPTH_PACKET_SIZE)))
                    .buyOrder(packet.getShort(position + DEPTH_BUY_ORDER_OFFSET + (i * DEPTH_PACKET_SIZE)))
                    .sellOrder(packet.getShort(position + DEPTH_SELL_ORDER_OFFSET + (i * DEPTH_PACKET_SIZE)))
                    .buyPrice(packet.getFloat(position + DEPTH_BUY_PRICE_OFFSET + (i * DEPTH_PACKET_SIZE)))
                    .sellPrice(packet.getFloat(position + DEPTH_SELL_PRICE_OFFSET + (i * DEPTH_PACKET_SIZE)))
                    .build();
            depthPacketList.add(depthPacket);
        }
        Tick tick = Tick.builder()
                .mbpRowPacket(depthPacketList)
                .lastTradedPrice(packet.getFloat(position + FULL_LTP_OFFSET))
                .lastTradedTime(EpochConverterUtil.epochConverter(packet.getInt(position + FULL_LTT_OFFSET)))
                .securityId(packet.getInt(position + FULL_SECURITY_ID_OFFSET))
                .tradable(packet.get(position + FULL_TRADABLE_OFFSET))
                .mode(packet.get(position + FULL_MODETYPE_OFFSET))
                .lastTradedQuantity(packet.getInt(position + FULL_LAST_TRADED_QUANTITY_OFFSET))
                .averageTradedPrice(packet.getFloat(position + FULL_AVG_TRADED_PRICE_OFFSET))
                .volumeTraded(packet.getInt(position + FULL_VOLUME_OFFSET))
                .totalBuyQuantity(packet.getInt(position + FULL_TOTAL_BUY_QUANTITY_OFFSET))
                .totalSellQuantity(packet.getInt(position + FULL_TOTAL_SELL_QUANTITY_OFFSET))
                .open(packet.getFloat(position + FULL_OPEN_OFFSET))
                .close(packet.getFloat(position + FULL_CLOSE_OFFSET))
                .high(packet.getFloat(position + FULL_HIGH_OFFSET))
                .low(packet.getFloat(position + FULL_LOW_OFFSET))
                .changePercent(packet.getFloat(position + FULL_CHANGE_PERCENT_OFFSET))
                .changeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + FULL_CHANGE_ABSOLUTE_OFFSET))))
                .fiftyTwoWeekHigh(packet.getFloat(position + FULL_52_WEEK_HIGH_OFFSET))
                .fiftyTwoWeekLow(packet.getFloat(position + FULL_52_WEEK_LOW_OFFSET))
                .oi(packet.getInt(position + FULL_OI_OFFSET))
                .oiChange(packet.getInt(position + FULL_CHANGE_OI_OFFSET))
                .build();
        ticks.add(tick);
    }
}