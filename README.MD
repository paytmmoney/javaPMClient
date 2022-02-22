# The Paytm Money Equity 1.0 API Java client

The official Java client for communicating with [PaytmMoney Equity API](https://www.paytmmoney.com/stocks/).

PMClient is a set of REST-like APIs that expose many capabilities required to build a complete investment and
trading platform. Execute orders in real time, manage user portfolio, and more, with the simple HTTP API collection.

[PaytmMoney Technology Pvt Ltd](https://www.paytmmoney.com/) (c) 2021. Licensed under the MIT License.

## Documentation

[comment]: <> (- [Kite Connect HTTP API documentation]&#40;https://kite.trade/docs/connect/v3/&#41;)

[comment]: <> (- [Java library documentation]&#40;https://kite.trade/docs/javakiteconnect/v3/&#41;)

## Usage

[comment]: <> (- [Download Kite Connect 3 jar file]&#40;https://github.com/zerodhatech/javakiteconnect/tree/master/dist&#41; and include it in your build path.)

- Include com.paytmmoney.equities.pmclient into build path from maven. Use version 3.8.2

- To use pmclient in **Android**, you need to include jar file in the libs directory and add the following line in
  you module's gradle file ``` compile files('libs/pmclient-1.0.0.jar') ```

## API usage

Create PMClient object to use SDK for any call Order or Account API.

```java
//Initialize PMClient using apiKey and apiSecretKey.User needs to create an object of SDK, by passing apiKey and SecretKey
PMClient pmClient=new PMClient("your_apiKey","your_api_secret_key","your_state_key");
// Initialize PMClient using apiKey, apiSecretKey and accessToken if you have already
PMClient pmClient=new PMClient("your_apiKey","your_api_secret_key","your_state_key","accessToken");
```

User need to generate session or set access token to use Api's

1) A user can call the login method and get the login URL.
2) A user manually executes a login url in the browser and fetches requestToken after authentication (validating
   username, pasword, OTP and passcode).
3) A user can call the generate_sessions method after pass requestToken to generate a session (access_token/jwt token)
4) Users can call the logout method to invalidate the session.

```java
//Get login url. Use this url in browser to login user, after authenticating user you will get requestToken. Use the same to get accessToken.
String url=pmClient.login();

// Once requestToken is obtained user can generate accessToken by calling generateSession,
String session=pmClient.generateSession("request_token");
```

After successfully generate session, All api use same session to call Paytmmoney Api's

```java
// Set access Token if you have already. In this case, Don't need to call generateSession method
pmClient.setAccessToken(userModel.accessToken);
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
* For converting through eDIS user needs to provide edis_txn_id & edis_auth_mode
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
UserHoldingDto userHoldingData=pmClient.getHoldingsData()
```

### Order Margin

* GET Order Margin Cal

```java
OrderMarginCalDto orderMargin=pmClient.getOrderMarginCalculator(source,exchange,segment,securityId,txnType,qty,price,product,triggerPrice)
```

### Order Margin Calculate

* Post Order Margin Cal

```java
ScriptMarginCalResDto scriptMarginCalResDto=pmClient.postScriptMarginCalculator(scriptMarginCalReqDto)
```

### Security Master

* Get security master

```java
//it returns response in csv format
String csv=pmClient.getSecurityMaster()
```

### eDis TPIN Generate

* Generate TPIN for eDIS

```java
TpinGenerateResDto tpinGenerateResDto=pmClient.generateEdisTpin()
```

### eDis Validate

* Validate for eDIS

```java
EdisResDto edisResDto=pmClient.validateEdisTpin(edisValidateReqDto)
```

### eDis status

* Get status for eDIS

```java
EdisResDto edisResDto=pmClient.getEdisStatus(edisReqId)
```