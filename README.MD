# The Paytm Money Equity 1.0 API Java client

The official Java client for communicating with [PaytmMoney Equity API](https://www.paytmmoney.com/stocks/).

PMClient is a set of REST-like APIs that expose many capabilities required to build a complete investment and
trading platform. Execute orders in real time, manage user portfolio, stream live market data (WebSockets), and more, with the simple HTTP API collection.

[PaytmMoney Technology Pvt Ltd](https://www.paytmmoney.com/) (c) 2022. Licensed under the MIT License.

## Api Documentation

- [PaytmMoney API documentation](https://developer.paytmmoney.com/docs/api/logout/)

## Usage

- [Download PMClient jar file](https://github.com/paytmmoney/javaPMClient/tree/master/dist) and include it in your build path.

- This jar will work only in springboot application.

- To use pmclient jar in your springboot app, add the jar file in libs directory of your project and then add the following line in your build.gradle ``` implementation files('libs/pmclient-1.1.0.jar') ```

- For maven project, you can add this line in pom.xml
- ``` java
  <dependency>
  <groupId>com.paytmmoney.equities</groupId>
  <artifactId>pmclient</artifactId>
  <version>1.1.0</version>
  <scope>system</scope>
  <systemPath>${basedir}/libs/pmclient-1.1.0.jar</systemPath>
  </dependency>
  ```

- Include com.paytmmoney.equities.pmclient into build path from maven. Use version 1.1.0

- To use pmclient in **Android**, you need to include jar file in the libs directory and add the following line in
  you module's gradle file ``` compile files('dist/pmclient-1.1.0.jar') ```

## API usage

Create PMClient object to use SDK for any call Order or Account API.

```java
//Initialize PMClient using apiKey and apiSecretKey.User needs to create an object of SDK, by passing apiKey and SecretKey
PMClient pmClient=new PMClient("your_apiKey","your_api_secret_key");
// Initialize PMClient using apiKey, apiSecretKey and jwt tokens if you have already
PMClient pmClient=new PMClient("your_apiKey","your_api_secret_key","accessToken","publicAccessToken","readAccessToken");
```

User need to generate session or set access token to use Api's

1) A user can call the login method and get the login URL.
2) A user manually executes a login url in the browser and fetches requestToken after authentication (validating
   username, password, OTP and passcode).
3) A user can call the generate_sessions method after pass requestToken to generate a session (access_token/jwt token)
4) Users can call the logout method to invalidate the session.

```java
//Get login url. Use this url in browser to login user, after authenticating user you will get requestToken. Use the same to get accessToken.
//stateKey : Variable key which merchant/fintech company expects Paytm Money to return with Request Token. This can be string.
String url=pmClient.login("stateKey");

// Once requestToken is obtained user can generate accessToken by calling generateSession,
String session=pmClient.generateSession("request_token");
```

After successfully generate session, All api use same session to call Paytmmoney Api's

### To manually set the jwt tokens,
```java
pm.set_access_token("your_access_token")
pm.set_public_access_token("your_public_access_token")
pm.set_read_access_token("your_read_access_token")
```

### Place Order

* Here user can place regular, cover and bracket orders.
* For cover order, user has to add trigger_price in argument
* For bracket order user has to add stoploss_value in argument.
* Prepare Order Body object and only fill params which is required for order

```java
//sample regular order request object
OrderReqDto orderReqDto=OrderReqDto.builder().txnType("B").exchange("NSE").segment("E").product("I").securityId("371").quantity(100L).validity("DAY").orderType("LMT").price(2085D).source("N").offMktFlag("false").build();
```

```java
//sample bracket order request object 
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("B").securityId("371").quantity(100L).validity("DAY").orderType("MKT").price(0D).stopLossValue(2d).profitValue(4d).build();
```

```java
//sample cover order request object 
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("V").securityId("11483").quantity(100L).validity("DAY").orderType("MKT").price(0D).triggerPrice(1690D).build();
```

```java
//Place order either for regular, either for cover or for bracket order, only request body should be changed OrderResDto
OrderResDto orderResDto=pmClient.placeOrder(orderReqDto);
```

### Modify Order

* Here user can modify regular, cover and bracket orders
* For cover order in argument user has to add leg_no
* For bracket order in argument user has to add leg_no & algo_order_no

```java
//sample regular modify order request object 
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("I").securityId("371").quantity(120L).validity("DAY").orderType("LMT").price(2084D).offMktFlag("false").mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId()).build();
```

```java
//sample bracket modify order request object
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("B").securityId("371").quantity(120L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false").mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId()).legNo(order.getLegNo()).algoOrderNo(order.getAlgoOrdNo()).build()
```

```java
//sample cover modify order request object
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("V").securityId("11483").quantity(120L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false").mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId()).legNo(order.getLegNo()).build()
```

```java
//Modify order either for regular, either for cover or for bracket order, only request body should be changed
OrderResDto orderResDto=pmClient.modifyOrder(OrderReqDto)
```

### Cancel Order

* Here User can Cancel Orders
* For cover order in argument user has to add leg_no
* For bracket order in argument user has to add leg_no & algo_order_no

```java
//sample regular cancel order request object 
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("I").securityId("371").quantity(50L).validity("DAY").orderType("LMT").price(2084D).offMktFlag("false").mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId()).build();
```

```java
//sample bracket cancel order request object
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("B").securityId("371").quantity(100L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false").mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId()).legNo(order.getLegNo()).algoOrderNo(order.getAlgoOrdNo()).build();
```

```java
//sample cover cancel order request object
OrderReqDto orderReqDto=OrderReqDto.builder().source("N").txnType("B").exchange("NSE").segment("E").product("V").securityId("11483").quantity(100L).validity("DAY").orderType("MKT").price(0D).offMktFlag("false").mktType("NL").orderNo(order.getOrderNo()).serialNo(order.getSerialNo()).groupId(order.getGroupId()).legNo(order.getLegNo()).build();
```

```java
//Cancel order either for regular, either for cover or for bracket order, only request body should be changed
OrderResDto orderResDto=pmClient.cancelOrder(OrderReqDto)
```

### Convert Order

* It will convert only regular order.
* The above details can be generated by TPIN APIs

```java
ConvertOrderReqDto conertOrder=ConvertOrderReqDto.builder().source("N").txnType("B").exchange("NSE").mktType("NL").segment("E").productFrom("I").productTo("I").quantity(100L).source("N").build();
```

```java
OrderResDto orderResDto=pmClient.convertOrder(conertOrder);
```
### Order Details

* Fetch details of all the order

```java
OrderBookDto orderBook=pmClient.orderBook();
```

### Position

* Get all the positions

```java
TradeDetailsDto tradeDetails=pmClient.getPosition();
```

### PositionDetails

* Get position detail of specific stock

```java
PositionDto position=pmClient.positionDetails(security_id,product,exchange);
```

### FundSummary

* it will give funds details

```java
FundSummaryDto fundSummary=pmClient.getFundSummary();
```

### Holdings value

* Get value of the holdings

```java
HoldingValueDto userHoldingValue=pmClient.getHoldingsValue();
```

### User Holdings Data

* Get holdings data of User

```java
UserHoldingDto userHoldingData=pmClient.getHoldingsData();
```

### Order Margin

* GET Order Margin Cal

```java
OrderMarginCalDto orderMargin=pmClient.getOrderMarginCalculator(source,exchange,segment,securityId,txnType,qty,price,product,triggerPrice);
```

### Order Margin Calculate

* Post Order Margin Cal

```java
ScriptMarginCalResDto scriptMarginCalResDto=pmClient.postScriptMarginCalculator(scriptMarginCalReqDto);
```

### Security Master

* Get security master
* User can filter by fileName

```java
//it returns response in csv format
String securityMaster=pmClient.getSecurityMaster("fileName");
```

### eDis TPIN Generate

* Generate TPIN for eDIS

```java
TpinGenerateResDto tpinGenerateResDto=pmClient.generateEdisTpin();
```

### eDis Validate

* Validate for eDIS

```java
EdisResDto edisResDto=pmClient.validateEdisTpin(edisValidateReqDto);
```

### eDis status

* Get status for eDIS

```java
EdisResDto edisResDto=pmClient.getEdisStatus(edisReqId);
```

### Create GTT
* To create a GTT order.
```java
GTTOrderResDto gTTOrderResDto=pmClient.createGtt(gttOrderReqDto);
```

### Get All GTT
* To get all GTT or get by pml_id or status.
```java
GTTGetAllResDto gTTGetAllResDto=pmClient.getAllGtt(pmlId,status);
```

### Get GTT
* To get GTT by Id.
```java
GTTOrderResDto gTTOrderResDto=pmClient.getGtt(id);
```

### Update GTT
* To update GTT by Id.
```java
GTTOrderResDto gTTOrderResDto=pmClient.updateGtt(id, gttOrderReqDto);
```

### Delete GTT
* To Delete GTT by Id.
```java
GTTOrderResDto gTTOrderResDto=pmClient.deleteGtt(id);
```

### Get Expiry
* To get expiry of the GTT.
```java
GTTOrderResDto gTTOrderResDto=pmClient.getGttExpiry(pmlId)
```

### Get Aggregate
* To get the aggregate of the GTTs.
```java
GTTAggregateResDto gTTAggregateResDto=pmClient.getGttAggregate()
```

### Get GTT InstructionId
* To GTT by InstructionId.
```java
GTTOrderResDto gTTOrderResDto=pmClient.getGttByInstructionId(id)
```

### Get Live Price via API
* To Get Live Price Data via API
```java
Object livePriceData=pmClient.getLiveMarketData("mode", "exchange", "scripId", "scripType")
```

### Get Option Chain
* To Get Option Chain using type, symbol and expiry (in DD-MM-YYYY format)
```java
Object optionChain=pmClient.getOptionChain("type", "symbol", "expiry")
```

### Get Option Chain Config
* To Get Option Chain Config using symbol
```java
Object optionChainConfig=pmClient.getLiveMarketData("symbol")
```

## WebSocket live streaming data
```java
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

        while (true) {
            // to make sure this method never terminates, and we keep on getting live data
        }
    }
```

* Call above method using the code below
```java
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
        tickerUsage("your_public_access_token", preferenceList);
```
For more details, take a look at Example.java in sample/src directory.