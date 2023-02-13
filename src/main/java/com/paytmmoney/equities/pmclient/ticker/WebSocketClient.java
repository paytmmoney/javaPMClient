package com.paytmmoney.equities.pmclient.ticker;


import com.google.gson.Gson;
import com.paytmmoney.equities.pmclient.constant.ApiConstants;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.DepthPacket;
import com.paytmmoney.equities.pmclient.model.PreferenceDto;
import com.paytmmoney.equities.pmclient.model.Tick;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

    /**
     * Initialize WebSocketClient.
     *
     * @param accessToken is the public access token received after successful login process.
     */
    public WebSocketClient(String accessToken) {
        createUrl(accessToken);
    }

    /**
     * Create a websocket connection with Broadcast server
     */
    public void connect() {
        try {
            ContainerProvider.getWebSocketContainer().connectToServer(this, URI.create(wsUrl));
        } catch (DeploymentException e) {
            if (onErrorListener != null)
                onErrorListener.onError(new ApplicationException("UNAUTHORIZED", 401));
        } catch (Exception e) {
            if (onErrorListener != null)
                onErrorListener.onError(e);
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
        if (onCloseListener != null)
            onCloseListener.onClose(reason.toString());
    }

    /**
     * This method invoked when any error occurs in websocket connection
     *
     * @param throwable contains error details
     */
    @OnError
    public void onError(Throwable throwable) {
        if (onErrorListener != null)
            onErrorListener.onError(throwable.getMessage());
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
        Tick tick = new Tick();
        tick.setLastTradedPrice(packet.getFloat(position + LTP_OFFSET));
        tick.setLastTradedTime(packet.getInt(position + INDEX_LTP_LUT_OFFSET));
        tick.setSecurityId(packet.getInt(position + INDEX_LTP_SECURITY_ID_OFFSET));
        tick.setTradable(packet.get(position + INDEX_LTP_TRADABLE_OFFSET));
        tick.setMode(packet.get(position + INDEX_LTP_MODETYPE_OFFSET));
        tick.setChangeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + INDEX_LTP_CHANGE_ABSOLUTE_OFFSET))));
        tick.setChangePercent(packet.getFloat(position + INDEX_LTP_CHANGE_PERCENT_OFFSET));
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
        Tick tick = new Tick();
        tick.setLastTradedPrice(packet.getFloat(position + LTP_OFFSET));
        tick.setSecurityId(packet.getInt(position + INDEX_QUOTE_SECURITY_ID_OFFSET));
        tick.setTradable(packet.get(position + INDEX_QUOTE_TRADABLE_OFFSET));
        tick.setMode(packet.get(position + INDEX_QUOTE_MODETYPE_OFFSET));
        tick.setOpen(packet.getFloat(position + INDEX_QUOTE_OPEN_OFFSET));
        tick.setClose(packet.getFloat(position + INDEX_QUOTE_CLOSE_OFFSET));
        tick.setHigh(packet.getFloat(position + INDEX_QUOTE_HIGH_OFFSET));
        tick.setLow(packet.getFloat(position + INDEX_QUOTE_LOW_OFFSET));
        tick.setChangePercent(packet.getFloat(position + INDEX_QUOTE_CHANGE_PERCENT_OFFSET));
        tick.setChangeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + INDEX_QUOTE_CHANGE_ABSOLUTE_OFFSET))));
        tick.setFiftyTwoWeekHigh(packet.getFloat(position + INDEX_QUOTE_52_WEEK_HIGH_OFFSET));
        tick.setFiftyTwoWeekLow(packet.getFloat(position + INDEX_QUOTE_52_WEEK_LOW_OFFSET));
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
        Tick tick = new Tick();
        tick.setLastTradedPrice(packet.getFloat(position + LTP_OFFSET));
        tick.setSecurityId(packet.getInt(position + INDEX_FULL_SECURITY_ID_OFFSET));
        tick.setTradable(packet.get(position + INDEX_FULL_TRADABLE_OFFSET));
        tick.setMode(packet.get(position + INDEX_FULL_MODETYPE_OFFSET));
        tick.setOpen(packet.getFloat(position + INDEX_FULL_OPEN_OFFSET));
        tick.setClose(packet.getFloat(position + INDEX_FULL_CLOSE_OFFSET));
        tick.setHigh(packet.getFloat(position + INDEX_FULL_HIGH_OFFSET));
        tick.setLow(packet.getFloat(position + INDEX_FULL_LOW_OFFSET));
        tick.setChangePercent(packet.getFloat(position + INDEX_FULL_CHANGE_PERCENT_OFFSET));
        tick.setChangeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + INDEX_FULL_CHANGE_ABSOLUTE_OFFSET))));
        tick.setLastTradedTime(packet.getInt(position + INDEX_FULL_LUT_OFFSET));
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
        Tick tick = new Tick();
        tick.setLastTradedPrice(packet.getFloat(position + LTP_OFFSET));
        tick.setLastTradedTime(packet.getInt(position + LTP_LTT_OFFSET));
        tick.setSecurityId(packet.getInt(position + LTP_SECURITY_ID_OFFSET));
        tick.setTradable(packet.get(position + LTP_TRADABLE_OFFSET));
        tick.setMode(packet.get(position + LTP_MODETYPE_OFFSET));
        tick.setChangeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + LTP_CHANGE_ABSOLUTE_OFFSET))));
        tick.setChangePercent(packet.getFloat(position + LTP_CHANGE_PERCENT_OFFSET));
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
        Tick tick = new Tick();
        tick.setLastTradedPrice(packet.getFloat(position + LTP_OFFSET));
        tick.setLastTradedTime(packet.getInt(position + QUOTE_LTT_OFFSET));
        tick.setSecurityId(packet.getInt(position + QUOTE_SECURITY_ID_OFFSET));
        tick.setTradable(packet.get(position + QUOTE_TRADABLE_OFFSET));
        tick.setMode(packet.get(position + QUOTE_MODETYPE_OFFSET));
        tick.setLastTradedQuantity(packet.getInt(position + QUOTE_LAST_TRADED_QUANTITY_OFFSET));
        tick.setAverageTradedPrice(packet.getFloat(position + QUOTE_AVG_TRADED_PRICE_OFFSET));
        tick.setVolumeTraded(packet.getInt(position + QUOTE_VOLUME_OFFSET));
        tick.setTotalBuyQuantity(packet.getInt(position + QUOTE_TOTAL_BUY_QUANTITY_OFFSET));
        tick.setTotalSellQuantity(packet.getInt(position + QUOTE_TOTAL_SELL_QUANTITY_OFFSET));
        tick.setOpen(packet.getFloat(position + QUOTE_OPEN_OFFSET));
        tick.setClose(packet.getFloat(position + QUOTE_CLOSE_OFFSET));
        tick.setHigh(packet.getFloat(position + QUOTE_HIGH_OFFSET));
        tick.setLow(packet.getFloat(position + QUOTE_LOW_OFFSET));
        tick.setChangePercent(packet.getFloat(position + QUOTE_CHANGE_PERCENT_OFFSET));
        tick.setChangeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + QUOTE_CHANGE_ABSOLUTE_OFFSET))));
        tick.setFiftyTwoWeekHigh(packet.getFloat(position + QUOTE_52_WEEK_HIGH_OFFSET));
        tick.setFiftyTwoWeekLow(packet.getFloat(position + QUOTE_52_WEEK_LOW_OFFSET));
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
        Tick tick = new Tick();
        ArrayList<DepthPacket> depthPacketList = new ArrayList<>();
        for (int i = 0; i < DEPTH_PACKETS_COUNT; i++) {
            DepthPacket depthPacket = new DepthPacket();
            depthPacket.setPacketNo(i + 1);
            depthPacket.setBuyQuantity(packet.getInt(DEPTH_BUY_QUANTITY_OFFSET + (i * DEPTH_PACKET_SIZE)));
            depthPacket.setSellQuantity(packet.getInt(DEPTH_SELL_QUANTITY_OFFSET + (i * DEPTH_PACKET_SIZE)));
            depthPacket.setBuyOrder(packet.getShort(DEPTH_BUY_ORDER_OFFSET + (i * DEPTH_PACKET_SIZE)));
            depthPacket.setSellOrder(packet.getShort(DEPTH_SELL_ORDER_OFFSET + (i * DEPTH_PACKET_SIZE)));
            depthPacket.setBuyPrice(packet.getFloat(DEPTH_BUY_PRICE_OFFSET + (i * DEPTH_PACKET_SIZE)));
            depthPacket.setSellPrice(packet.getFloat(DEPTH_SELL_PRICE_OFFSET + (i * DEPTH_PACKET_SIZE)));
            depthPacketList.add(depthPacket);
        }

        tick.setMbpRowPacket(depthPacketList);
        tick.setLastTradedPrice(packet.getFloat(position + FULL_LTP_OFFSET));
        tick.setLastTradedTime(packet.getInt(position + FULL_LTT_OFFSET));
        tick.setSecurityId(packet.getInt(position + FULL_SECURITY_ID_OFFSET));
        tick.setTradable(packet.get(position + FULL_TRADABLE_OFFSET));
        tick.setMode(packet.get(position + FULL_MODETYPE_OFFSET));
        tick.setLastTradedQuantity(packet.getInt(position + FULL_LAST_TRADED_QUANTITY_OFFSET));
        tick.setAverageTradedPrice(packet.getFloat(position + FULL_AVG_TRADED_PRICE_OFFSET));
        tick.setVolumeTraded(packet.getInt(position + FULL_VOLUME_OFFSET));
        tick.setTotalBuyQuantity(packet.getInt(position + FULL_TOTAL_BUY_QUANTITY_OFFSET));
        tick.setTotalSellQuantity(packet.getInt(position + FULL_TOTAL_SELL_QUANTITY_OFFSET));
        tick.setOpen(packet.getFloat(position + FULL_OPEN_OFFSET));
        tick.setClose(packet.getFloat(position + FULL_CLOSE_OFFSET));
        tick.setHigh(packet.getFloat(position + FULL_HIGH_OFFSET));
        tick.setLow(packet.getFloat(position + FULL_LOW_OFFSET));
        tick.setChangePercent(packet.getFloat(position + FULL_CHANGE_PERCENT_OFFSET));
        tick.setChangeAbsolute(Float.parseFloat(decimalFormat.format(packet.getFloat(position + FULL_CHANGE_ABSOLUTE_OFFSET))));
        tick.setFiftyTwoWeekHigh(packet.getFloat(position + FULL_52_WEEK_HIGH_OFFSET));
        tick.setFiftyTwoWeekLow(packet.getFloat(position + FULL_52_WEEK_LOW_OFFSET));
        tick.setOi(packet.getInt(position + FULL_OI_OFFSET));
        tick.setOiChange(packet.getInt(position + FULL_CHANGE_OI_OFFSET));
        ticks.add(tick);
    }
}