import com.paytmmoney.equities.pmclient.PMClient;
import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.PreferenceDto;
import com.paytmmoney.equities.pmclient.model.Tick;
import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
import com.paytmmoney.equities.pmclient.request.EdisIsin;
import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
import com.paytmmoney.equities.pmclient.request.OrderReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDto;
import com.paytmmoney.equities.pmclient.request.ScriptMarginCalReqDtoList;
import com.paytmmoney.equities.pmclient.response.FundSummaryDto;
import com.paytmmoney.equities.pmclient.response.HoldingValueDto;
import com.paytmmoney.equities.pmclient.response.LivePriceDataListDto;
import com.paytmmoney.equities.pmclient.response.OptionChainConfigDto;
import com.paytmmoney.equities.pmclient.response.OptionChainDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDataDto;
import com.paytmmoney.equities.pmclient.response.OrderBookDto;
import com.paytmmoney.equities.pmclient.response.OrderMarginCalDto;
import com.paytmmoney.equities.pmclient.response.OrderResDto;
import com.paytmmoney.equities.pmclient.response.PositionDetailDto;
import com.paytmmoney.equities.pmclient.response.PositionDto;
import com.paytmmoney.equities.pmclient.response.ScriptMarginCalResDto;
import com.paytmmoney.equities.pmclient.response.TradeDetailsDto;
import com.paytmmoney.equities.pmclient.response.UserDetailsResDto;
import com.paytmmoney.equities.pmclient.response.UserHoldingDto;
import com.paytmmoney.equities.pmclient.ticker.OnCloseListener;
import com.paytmmoney.equities.pmclient.ticker.OnErrorListener;
import com.paytmmoney.equities.pmclient.ticker.OnMessageListener;
import com.paytmmoney.equities.pmclient.ticker.OnOpenListener;
import com.paytmmoney.equities.pmclient.ticker.WebSocketClient;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Example {

    PMClient pmClient = null;


    public static void main(String[] args) {
        Example t = new Example();
        t.executeApis();
        ArrayList<PreferenceDto> preferenceList = new ArrayList<>();

        /**
         * PreferenceDto Format
         *
         actionType - 'ADD', 'REMOVE'
         modeType - 'LTP', 'FULL', 'QUOTE'
         scripType - 'ETF', 'FUTURE', 'INDEX', 'OPTION', 'EQUITY'
         exchangeType - 'BSE', 'NSE'
         scripId -
         */
        preferenceList.add(new PreferenceDto(
                "ADD",
                "FULL",
                "EQUITY",
                "NSE",
                "3456"));
        t.tickerUsage("your_public_access_token", preferenceList);
    }

    public void executeApis() {
        pmClient = new PMClient("api_key",
                "api_secret");
        try {
            System.out.println(pmClient.login("state_key"));
            String str = "";
            str = pmClient.generateSession("request_token");

//            pmClient.setAccessToken("access_token");
//            pmClient.setPublicAccessToken("public_access_token");
//            pmClient.setReadAccessToken("read_access_token");

            // To check user details
            UserDetailsResDto userDetailsResDto = pmClient.getUserDetails();
            System.out.println("userDetailsDto:" + userDetailsResDto.toString());

            // To check live price
            String preferences = "NSE:13:INDEX,NSE:6705:EQUITY";
            LivePriceDataListDto livePriceData = pmClient.getLiveMarketData("FULL", preferences);
            System.out.println("livePriceData:" + livePriceData);

            // To check Funds Summary api
            FundSummaryDto fundSummaryDto = pmClient.getFundSummary();
            System.out.println("fundSummaryDto:" + fundSummaryDto.toString());

            //To check order book api
            OrderBookDto orderBookDto = pmClient.getOrderBook();
            System.out.println("orderBookDto:" + orderBookDto.toString());

            //To check positions api
            PositionDto positionDto = pmClient.getPosition();
            System.out.println("positionDto:" + positionDto.toString());

            //To check Holding value api
            HoldingValueDto holdingsValue = pmClient.getHoldingsValue();
            System.out.println("holdingsValue:" + holdingsValue.toString());

            //To check user holdings api
            UserHoldingDto userHoldingDto = pmClient.getHoldingsData();
            System.out.println("userHoldingDto:" + userHoldingDto.toString());

            //To get margin calculator
            OrderMarginCalDto orderMarginValue = pmClient.getOrderMarginCalculator("N", "NSE", "E", "772", "B", 100, 0.00, "C", 0.00);
            System.out.println("Order Margin value api details : " + orderMarginValue.toString());

            //To get trade details
            TradeDetailsDto tradeDetailValue = pmClient.getTradeDetails("812201042761", "1", "E");
            System.out.println("Trade details: " + tradeDetailValue.toString());

            //To get position details api response code
            PositionDetailDto positionDetailsValue = pmClient.getPositionDetails("532215", "C", "BSE");
            System.out.println("Position details API response: " + positionDetailsValue.toString());

            //To get scrips Margin calculator api response code
            ScriptMarginCalResDto scriptMarginCalResDto = pmClient.postScriptMarginCalculator(getScriptMarginCalReqDto());
            System.out.println("Scrip margin calc API response: " + scriptMarginCalResDto.toString());

//            //To check eDIS flows
//            //Generate Tpin
//            TpinGenerateResDto tPinGenResp = pmClient.generateEdisTpin();
//            System.out.println(tPinGenResp.toString());
//
//            //Validate Tpin
//            EdisValidateReqDto validateReqDto = getEdisValidateReqDto();
//            EdisResDto validateTPinEdisResp = pmClient.validateEdisTpin(validateReqDto);
//            System.out.println(validateTPinEdisResp.toString());
//
//            //Get eDIS status
//            EdisStatusResDto resp = pmClient.getEdisStatus("16204992");
//            System.out.println(resp);

//            //place eDIS order
//            OrderResDto placeEIDSOrderResp = pmClient.placeOrder(getRegularPlaceEDISOrder());
//            System.out.println(placeEIDSOrderResp.toString());
//
//            //Modify eDIS order
//            OrderResDto modifyEIDSOrderResp = pmClient.modifyOrder(getRegularModifyEDISOrder());
//            System.out.println(modifyEIDSOrderResp.toString());
//
//            //Convert eDIS order
//            OrderResDto convertEIDSOrderResp = pmClient.convertOrder(getConvertRegularEDISOrder());
//            System.out.println(convertEIDSOrderResp.toString());


          /*  OrderReqDto orderReqDto = getRegularPlaceOrder();
            OrderResDto resp = pmClient.placeOrder(orderReqDto);
            System.out.println(resp.toString());*/


            //To get security master api details
            String securityMasterValue = pmClient.getSecurityMaster("fileName");
            //  System.out.println("Security master value details : " + securityMasterValue.toString());
            FileWriter writer = new FileWriter("securityMasterData.csv");
            writer.append(securityMasterValue);
            writer.flush();
            writer.close();

//          String csv = pmClient.getSecurityMaster();
//          System.out.println("csv:"+ csv);
            executeOrder(null, null, "new", "regular");
            executeOrder(null, null, "new", "bracket");
            executeOrder(null, null, "new", "cover");

            //To get option chain details
            OptionChainDto optionChain = pmClient.getOptionChain("CALL", "symbol", "expiry");
            System.out.println("optionChain:" + optionChain);

            //To get option chain config details
            OptionChainConfigDto optionChainConfig = pmClient.getOptionChainConfig("symbol");
            System.out.println("optionChainConfig:" + optionChainConfig);

            //   executeConvertOrder(null, null, "new");

            // To convert regular order

         /*   OrderResDto orderRespDto = pmClient.placeOrder(getRegularPlaceOrder());
            System.out.println("placed regular order:" + orderRespDto.toString());

            String orderNum = orderRespDto.getData().get(0).getOrderNo();
            System.out.println("Order placed for conversion purpose : " + orderNum);
            orderResDto = pmClient.convertOrder(getConvertRegularOrder());
            System.out.println("placed regular convert order:" + orderResDto.toString());
            orderBookDto = pmClient.getOrderBook();
            System.out.println("orderBookDto:" + orderBookDto.toString());
*/

/*
            // To logout
            String logoutResp = pmClient.logout();
            System.out.println("Check if user is logged out: " + logoutResp);


//            orderResDto = pmClient.placeOrder(t.getBracketPlaceOrder());
//            System.out.println("place regular order:"+ orderResDto.toString());

            str = pmClient.logout();*/
        } catch (ApplicationException e) {
            e.printStackTrace();
            System.out.println("exception in generate session :" + e.getMessage() + " code:" + e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("general exception : " + e);
        }
    }


    public void executeOrder(String orderNo, OrderBookDto orderBookDto, String orderState, String orderType) throws Exception {
        OrderReqDto orderReqDto = null;
        if (orderState.equals("new")) {
            switch (orderType) {
                case "regular":
                    orderReqDto = getRegularPlaceOrder();
                    break;
                case "bracket":
                    orderReqDto = getBracketPlaceOrder();
                    break;
                case "cover":
                    orderReqDto = getCoverPlaceOrder();
                    break;
            }
            OrderResDto orderResDto = pmClient.placeOrder(orderReqDto);

            System.out.println("place " + orderType + " order:" + orderResDto.toString());

            orderBookDto = pmClient.getOrderBook();
            System.out.println("orderBookDto:" + orderBookDto.toString());
            orderNo = orderResDto.getData().get(0).getOrderNo();
            executeOrder(orderNo, orderBookDto, "modify", orderType);
            // executeOrder(orderNo, orderBookDto, "cancel", orderType);
            return;
        }

        for (OrderBookDataDto o : orderBookDto.getData()) {
            if (o.getOrderNo().equals(orderNo)) {
                if (orderState.equals("modify")) {
                    switch (orderType) {
                        case "regular":
                            orderReqDto = getRegularModifyOrder(o);
                            break;
                        case "bracket":
                            orderReqDto = getBracketModifyOrder(o);
                            break;
                        case "cover":
                            orderReqDto = getCoverModifyOrder(o);
                            break;
                    }
                    OrderResDto orderResDto = pmClient.modifyOrder(orderReqDto);
                    System.out.println("place regular modify order:" + orderResDto.toString());
                } else if (orderState.equals("cancel")) {
                    switch (orderType) {
                        case "regular":
                            orderReqDto = getRegularCancelOrder(o);
                            break;
                        case "bracket":
                            orderReqDto = getBracketCancelOrder(o);
                            break;
                        case "cover":
                            orderReqDto = getCoverCancelOrder(o);
                            break;
                    }
                    OrderResDto orderResDto = pmClient.cancelOrder(orderReqDto);
                    System.out.println("place regular cancel order:" + orderResDto.toString());
                }
                orderBookDto = pmClient.getOrderBook();
                System.out.println("orderBookDto:" + orderBookDto.toString());
                if (orderState.equals("modify")) {
                    executeOrder(orderNo, orderBookDto, "cancel", orderType);
                }
            }

        }
    }


    public void executeConvertOrder(String orderNo, OrderBookDto orderBookDto, String orderState) throws Exception {
        {
            OrderReqDto orderReqDto = null;

            if (orderState.equals("new")) {
                ConvertOrderReqDto convertOrderReqDto = null;
                OrderResDto orderResDto = pmClient.placeOrder(getRegularPlaceOrder());
                System.out.println("place regular " + " order:" + orderResDto.toString());
                convertOrderReqDto = getConvertRegularOrder();
                OrderResDto orderRespDto = pmClient.convertOrder(convertOrderReqDto);
                System.out.println("place regular convert order:" + orderRespDto.toString());
            }
            orderBookDto = pmClient.getOrderBook();
            System.out.println("orderBookDto:" + orderBookDto.toString());

        }

    }


    public OrderReqDto getRegularPlaceOrder() {
        return OrderReqDto.builder().txnType("B").exchange("NSE").segment("E").product("I").securityId("772")
                .quantity(1L).validity("DAY").orderType("LMT").price(560.55).source("N").offMktFlag("false")
                .build();
    }

    public OrderReqDto getRegularModifyOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("B").exchange("NSE").segment("E").product("I").securityId("772")
                .quantity(5L).validity("DAY").orderType("LMT").price(560.55).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .build();
    }

    public OrderReqDto getRegularCancelOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("S").exchange("BSE").segment("E").product("I").securityId("500209")
                .quantity(10L).validity("DAY").orderType("LMT").price(1820D).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .build();
    }

    public ConvertOrderReqDto getRegularConvertOrder(OrderBookDataDto order) {
        return ConvertOrderReqDto.builder().source("N").txnType("B").exchange("NSE").mktType("NL")
                .segment("E").productFrom("I").productTo("C").quantity(100L).source("N")
                .build();
    }

    public OrderReqDto getRegularExitOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("B").exchange("NSE").segment("E").product("I").securityId("371")
                .quantity(100L).validity("DAY").orderType("LMT").price(1820D).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .build();
    }

    //{"source":"W","txn_type":"B","exchange":"NSE","segment":"E","product":"B","security_id":371,"quantity":1,
    // "validity":"DAY","order_type":"MKT","price":0,"trigger_price":null,"stoploss_value":"2","profit_value":"4"}
    public OrderReqDto getBracketPlaceOrder() {
        return OrderReqDto.builder()
                .source("N").txnType("S").exchange("NSE").segment("E").product("B").securityId("11483")
                .quantity(100L).validity("DAY").orderType("MKT").price(0D).stopLossValue(2d).profitValue(4d)
                .build();
    }

    public OrderReqDto getBracketModifyOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("S").exchange("NSE").segment("E").product("B").securityId("11483")
                .quantity(120L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .legNo(order.getLegNo()).algoOrderNo(order.getAlgoOrdNo())
                .build();
    }

    public OrderReqDto getBracketCancelOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("S").exchange("NSE").segment("E").product("B").securityId("11483")
                .quantity(100L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .legNo(order.getLegNo()).algoOrderNo(order.getAlgoOrdNo())
                .build();
    }

    public OrderReqDto getCoverPlaceOrder() {
        return OrderReqDto.builder()
                .source("N").txnType("B").exchange("NSE").segment("E").product("V").securityId("11483")
                .quantity(100L).validity("DAY").orderType("MKT").price(0D).triggerPrice(1690D)
                .build();
    }

    public OrderReqDto getCoverModifyOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("B").exchange("NSE").segment("E").product("V").securityId("11483")
                .quantity(120L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .legNo(order.getLegNo())
                .build();
    }

    public OrderReqDto getCoverCancelOrder(OrderBookDataDto order) {
        return OrderReqDto.builder()
                .source("N").txnType("B").exchange("NSE").segment("E").product("V").securityId("11483")
                .quantity(100L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false")
                .mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId())
                .legNo(order.getLegNo())
                .build();
    }

    public ConvertOrderReqDto getConvertRegularOrder() {
        return ConvertOrderReqDto.builder()
                .source("M").txnType("B").exchange("NSE").mktType("NL").segment("E")
                .productFrom("I").productTo("C").quantity(50L).securityId("371")
                .build();
    }


    public ScriptMarginCalReqDto getScriptMarginCalReqDto() {

        List<ScriptMarginCalReqDtoList> marginList = new ArrayList<>();

        ScriptMarginCalReqDtoList scriptMarginCalReqDtoList1 = ScriptMarginCalReqDtoList.builder()
                .exchange("NSE")
                .instrument("EQUITY")
                .quantity("1")
                .securityId("1594")
                .segment("E")
                .strikePrice("0")
                .triggerPrice("0")
                .txnType("B")
                .build();
        marginList.add(scriptMarginCalReqDtoList1);

        ScriptMarginCalReqDtoList scriptMarginCalReqDtoList2 = ScriptMarginCalReqDtoList.builder()
                .exchange("NSE")
                .instrument("EQUITY")
                .quantity("10")
                .securityId("772")
                .segment("E")
                .strikePrice("0")
                .triggerPrice("0")
                .txnType("B")
                .build();
        marginList.add(scriptMarginCalReqDtoList2);
        return ScriptMarginCalReqDto.builder().source("N").marginList(marginList).build();
    }

    public EdisValidateReqDto getEdisValidateReqDto() {

        List<EdisIsin> isinList = new ArrayList<>();

        EdisIsin edisIsin1 = EdisIsin.builder()
                .isin("INE016A01026")
                .qty(100L)
                .build();
        isinList.add(edisIsin1);

        EdisIsin edisIsin2 = EdisIsin.builder()
                .isin("INE081A01012")
                .qty(100L)
                .build();
        isinList.add(edisIsin2);

        EdisIsin edisIsin3 = EdisIsin.builder()
                .isin("INE075A01022")
                .qty(100L)
                .build();

        isinList.add(edisIsin3);
        EdisValidateReqDto resp = EdisValidateReqDto.builder().tradeType("PRE").isinList(isinList).build();
        System.out.println(resp.toString());
        return resp;


    }

    public OrderReqDto getRegularPlaceEDISOrder() {
        return OrderReqDto.builder().txnType("S").exchange("NSE").segment("E").product("C").securityId("772")
                .quantity(100L).validity("DAY").orderType("MKT").price(0D).source("N").offMktFlag("false")
                .build();
    }

    public OrderReqDto getRegularModifyEDISOrder() {
        return OrderReqDto.builder()
                .source("N").txnType("S").exchange("NSE").segment("E").product("C").securityId("772")
                .quantity(100L).validity("DAY").orderType("LMT").price(550D).offMktFlag("false")
                .mktType("NL").orderNo("812201122671").groupId(8).serialNo(5)
                .build();
    }

    public ConvertOrderReqDto getConvertRegularEDISOrder() {
        return ConvertOrderReqDto.builder()
                .source("M").txnType("B").exchange("NSE").mktType("NL").segment("E")
                .productFrom("C").productTo("I").quantity(120L).securityId("772")
                .build();
    }

    /**
     * Demonstrates Broadcast connection, subscribing for preferences
     *
     * @param accessToken public access token
     * @param preferences list of preferences to be subscribed
     */
    public void tickerUsage(String accessToken, ArrayList<PreferenceDto> preferences) {

        /** To get live price use websocket connection.
         * It is recommended to use only one websocket connection at any point of time and make sure you stop connection, once user goes out of app.
         */
        final WebSocketClient webSocketClient = new WebSocketClient(accessToken);

        /** To use reconnect feature, comment above line and uncomment below line.
         * Pass doReconnect param as true and in maxReconnectAttempt param, the no. of times you want to retry to create connection.
         */
//        final WebSocketClient webSocketClient = new WebSocketClient(accessToken, true, 5);

        /** Set onOpen listener to listen to connection open event.*/
        webSocketClient.setOnOpenListener(new OnOpenListener() {
            @Override
            public void onOpen() {
                /** Subscribe list of preferences.
                 * */
                webSocketClient.subscribe(preferences);
            }
        });

        /** Set onClose listener to listen to connection close event.*/
        webSocketClient.setOnCloseListener(new OnCloseListener() {
            @Override
            public void onClose(String closeReason) {
                System.out.println("Connection Closed: " + closeReason);
            }
        });

        /** Set onError listener to listen to errors.*/
        webSocketClient.setOnErrorListener(new OnErrorListener() {

            @Override
            public void onError(Exception exception) {
                System.out.println("Exception in WebSocketClient: " + exception.getMessage());
            }

            @Override
            public void onError(ApplicationException applicationException) {
                //handle here.
                System.out.println("Exception in WebSocketClient: " + applicationException.message + " statusCode: " + applicationException.getCode());
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println("Exception occurred for this session: " + errorMessage);
            }
        });

        /** Set onMessage listener to listen to message arrival event.*/
        webSocketClient.setOnMessageListener(new OnMessageListener() {
            @Override
            public void onMessage(ArrayList<Tick> response) {
                for (Tick tick : response)
                    System.out.println("tick: " + tick);
            }
        });

        /** create a websocket connection with broadcast server.*/
        webSocketClient.connect();

        /** To explicitly close websocket connection with server, uncomment this method call*/
//        webSocketClient.closeConnection();

        while (true) {
            // to make sure this method never terminates, and we keep on getting live data
        }
    }
}