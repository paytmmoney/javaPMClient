package com.paytmmoney.equities.pmclient.constant;

public class ApiConstants {
    public static final String LOGIN_HOST = "https://login.paytmmoney.com";
    public static final String LOGIN_URL_PARAM = "&state=";
    public static final String API_HOST = "https://developer.paytmmoney.com";
    public static final String LOGIN_URL = LOGIN_HOST + "/merchant-login?apiKey=";
    public static final String ACCESS_TOKEN_URL = API_HOST + "/accounts/v1/gettoken";
    public static final String USER_DETAILS_URL = API_HOST + "/accounts/v1/user/details";
    public static final String LOGOUT_ENDPOINT = API_HOST + "/accounts/v1/logout";
    public static final String ORDER_BOOK_ENDPOINT = API_HOST + "/orders/v1/order-book";
    public static final String TRADE_DETAILS_ENDPOINT = API_HOST + "/orders/v1/trade-details";
    public static final String POSITION_ENDPOINT = API_HOST + "/orders/v1/position";
    public static final String POSITION_DETAIL_ENDPOINT = API_HOST + "/orders/v1/position-details";
    public static final String HOLDINGS_VALUE_ENDPOINT = API_HOST + "/holdings/v1/get-holdings-value";
    public static final String HOLDINGS_DATA_ENDPOINT = API_HOST + "/holdings/v1/get-user-holdings-data";
    public static final String FUND_SUMMARY_ENDPOINT = API_HOST + "/accounts/v1/funds/summary";
    public static final String MARGIN_CALCULATOR_ENDPOINT = API_HOST + "/margin/v1/scrips/calculator";
    public static final String ORDER_MARGIN_CALCULATOR_ENDPOINT = API_HOST + "/margin/v1/order/calculator";
    public static final String REGULAR_ORDER_ENDPOINT = API_HOST + "/orders/v1/place/regular";
    public static final String REGULAR_MODIFY_ORDER_ENDPOINT = API_HOST + "/orders/v1/modify/regular";
    public static final String REGULAR_CANCEL_ORDER_ENDPOINT = API_HOST + "/orders/v1/cancel/regular";
    public static final String REGULAR_CONVERT_ORDER_ENDPOINT = API_HOST + "/orders/v1/convert/regular";
    public static final String COVER_ORDER_ENDPOINT = API_HOST + "/orders/v1/place/cover";
    public static final String COVER_MODIFY_ORDER_ENDPOINT = API_HOST + "/orders/v1/modify/cover";
    public static final String COVER_CANCEL_ORDER_ENDPOINT = API_HOST + "/orders/v1/exit/cover";
    public static final String BRACKET_ORDER_ENDPOINT = API_HOST + "/orders/v1/place/bracket";
    public static final String BRACKET_MODIFY_ORDER_ENDPOINT = API_HOST + "/orders/v1/modify/bracket";
    public static final String BRACKET_CANCEL_ORDER_ENDPOINT = API_HOST + "/orders/v1/exit/bracket";
    public static final String SECURITY_MASTER_ENDPOINT = API_HOST + "/data/v1/security-master";
    public static final String EDIS_TPIN_GENERATE_ENDPOINT = API_HOST + "/edis/v1/generate/tpin";
    public static final String EDIS_TPIN_VALIDATION_ENDPOINT = API_HOST + "/edis/v1/validate/tpin";
    public static final String EDIS_TPIN_STATUS_ENDPOINT = API_HOST + "/edis/v1/status";
    public static final String PRICE_CHART_DETAIL_ENDPOINT = API_HOST + "/data/v1/price-charts/sym";


    public final static String TEXT = "text";
    public static final String CSV = "csv";
    public static final String API_KEY = "apiKey";
    public static final String REQUEST_TOKEN = "requestToken";
    public static final String MERCHANT_SECRET = "merchantSecret";
    public static final String X_JWT_TOKEN = "x-jwt-token";

    public static final String ORDER_NO = "order_no";
    public static final String LEG_NO = "leg_no";
    public static final String SEGMENT = "segment";
    public static final String SECURITY_ID = "security_id";
    public static final String PRODUCT = "product";
    public static final String EXCHANGE = "exchange";
    public static final String CONFIG = "config";
    public static final String SOURCE = "source";
    public static final String TXN_TYPE = "txn_type";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String TRIGGER_PRICE = "trigger_price";
    public static final String EDIS_REQUEST_ID = "edis_request_id";
    public static final String SCRIP_TYPE = "scrip_type";
    public static final String DATA = "data";
    public static final String CONT = "cont";
    public static final String EXPIRY = "expiry";
    public static final String FROM_DATE = "fromDate";
    public static final String INSTRUMENT_TYPE = "instType";
    public static final String INTERVAL = "interval";
    public static final String MONTH_ID = "monthId";
    public static final String SERIES = "series";
    public static final String STRIKE = "strike";
    public static final String SYMBOL = "symbol";
    public static final String TO_DATE = "toDate";

}
