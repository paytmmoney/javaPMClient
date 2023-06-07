package com.paytmmoney.equities.pmclient.constant;

public class ApiConstants {

    // Tokens
    public static final String ACCESS_TOKEN = "access_token";
    public static final String PUBLIC_ACCESS_TOKEN = "public_access_token";
    public static final String READ_ACCESS_TOKEN = "read_access_token";

    // Login Endpoints
    public static final String LOGIN_HOST = "https://login.paytmmoney.com";
    public static final String LOGIN_URL_PARAM = "&state=";
    public static final String API_HOST = "https://developer.paytmmoney.com";
    public static final String LOGIN_URL = LOGIN_HOST + "/merchant-login?apiKey=";
    public static final String ACCESS_TOKEN_URL = API_HOST + "/accounts/v2/gettoken";
    public static final String[][] USER_DETAILS_URL = {{API_HOST + "/accounts/v1/user/details"}, {ACCESS_TOKEN, READ_ACCESS_TOKEN}};
    public static final String[][] LOGOUT_ENDPOINT = {{API_HOST + "/accounts/v1/logout"}, {ACCESS_TOKEN, PUBLIC_ACCESS_TOKEN, READ_ACCESS_TOKEN}};

    // order-book, trade details endpoints
    public static final String[][] ORDER_BOOK_ENDPOINT = {{API_HOST + "/orders/v1/order-book"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] ORDERS_ENDPOINT = {{API_HOST + "/orders/v1/user/orders"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] TRADE_DETAILS_ENDPOINT = {{API_HOST + "/orders/v1/trade-details"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // positions and holdings endpoints
    public static final String[][] POSITION_ENDPOINT = {{API_HOST + "/orders/v1/position"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] POSITION_DETAIL_ENDPOINT = {{API_HOST + "/orders/v1/position-details"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] HOLDINGS_VALUE_ENDPOINT = {{API_HOST + "/holdings/v1/get-holdings-value"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] HOLDINGS_DATA_ENDPOINT = {{API_HOST + "/holdings/v1/get-user-holdings-data"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] FUND_SUMMARY_ENDPOINT = {{API_HOST + "/accounts/v1/funds/summary"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // margin endpoints
    public static final String[][] MARGIN_CALCULATOR_ENDPOINT = {{API_HOST + "/margin/v1/scrips/calculator"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] ORDER_MARGIN_CALCULATOR_ENDPOINT = {{API_HOST + "/margin/v1/order/calculator"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // regular order endpoints
    public static final String[][] PLACE_REGULAR_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/place/regular"},{ACCESS_TOKEN}};
    public static final String[][] REGULAR_MODIFY_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/modify/regular"},{ACCESS_TOKEN}};
    public static final String[][] REGULAR_CANCEL_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/cancel/regular"},{ACCESS_TOKEN}};
    public static final String[][] REGULAR_CONVERT_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/convert/regular"},{ACCESS_TOKEN}};

    // cover order endpoints
    public static final String[][] PLACE_COVER_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/place/cover"},{ACCESS_TOKEN}};
    public static final String[][] COVER_MODIFY_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/modify/cover"},{ACCESS_TOKEN}};
    public static final String[][] COVER_CANCEL_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/exit/cover"},{ACCESS_TOKEN}};

    // bracket order endpoints
    public static final String[][] PLACE_BRACKET_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/place/bracket"},{ACCESS_TOKEN}};
    public static final String[][] BRACKET_MODIFY_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/modify/bracket"},{ACCESS_TOKEN}};
    public static final String[][] BRACKET_CANCEL_ORDER_ENDPOINT = {{API_HOST + "/orders/v1/exit/bracket"},{ACCESS_TOKEN}};

    // security master endpoints
    public static final String SECURITY_MASTER_ENDPOINT = API_HOST + "/data/v1/scrips/";

    // edis endpoints
    public static final String[][] EDIS_TPIN_GENERATE_ENDPOINT = {{API_HOST + "/edis/v1/generate/tpin"},{ACCESS_TOKEN}};
    public static final String[][] EDIS_TPIN_VALIDATION_ENDPOINT = {{API_HOST + "/edis/v1/validate/tpin"},{ACCESS_TOKEN}};
    public static final String[][] EDIS_TPIN_STATUS_ENDPOINT = {{API_HOST + "/edis/v1/status"},{ACCESS_TOKEN}};

    // historical data endpoints
    public static final String[][] PRICE_CHART_DETAIL_ENDPOINT = {{API_HOST + "/data/v1/price-charts/sym"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // gtt endpoints
    public static final String[][] GTT = {{API_HOST + "/gtt/v1/gtt"},{ACCESS_TOKEN}};
    public static final String[][] GTT_AGGREGATE = {{GTT[0][0] + "/aggregate"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] GTT_EXPIRY = {{GTT[0][0] + "/expiry-date"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] GTT_BY_INSTRUCTION_ID = {{GTT[0][0] + "/instructions"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // live market data endpoints
    public static final String[][] LIVE_MARKET_DATA = {{API_HOST + "/data/v1/price/live"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // fno endpoints
    public static final String[][] FNO_OPTION_CHAIN = {{API_HOST+"/fno/v1/option-chain"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};
    public static final String[][] FNO_OPTION_CHAIN_CONFIG = {{API_HOST+"/fno/v1/option-chain/config"},{ACCESS_TOKEN,READ_ACCESS_TOKEN}};

    // websocket endpoint
    public static final String WEBSOCKET_URL = "wss://developer-ws.paytmmoney.com/broadcast/user/v1/data?x_jwt_token=";


    public final static String TEXT = "text";
    public static final String CSV = "csv";
    public static final String API_KEY = "api_key";
    public static final String API_SECRET_KEY = "api_secret_key";
    public static final String REQUEST_TOKEN = "request_token";
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

    public static final String META = "meta";
    public static final String ID = "id";
    public static final String INSTRUCTION_ID = "instruction_id";
    public static final String NAME = "name";
    public static final String USER_ID = "user_id";
    public static final String STATUS = "status";
    public static final String CANCELLATION_CODE = "cancellation_code";
    public static final String CANCELLATION_REASON = "cancellation_reason";
    public static final String EXPIRY_DATE = "expiry_date";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String DELETED_AT = "deleted_at";
    public static final String REQUEST_METADATA = "request_metadata";
    public static final String DISPLAY_MESSAGE = "displayMessage";
    public static final String ORDER_TYPE = "order_type";
    public static final String PML_ID = "pml_id";
    public static final String PML_ID_PARAM = "pml-id";
    public static final String PRODUCT_TYPE = "product_type";
    public static final String SET_PRICE = "set_price";
    public static final String TRANSACTION_DETAILS = "transaction_details";
    public static final String EXECUTION_REF_ID = "execution_ref_id";
    public static final String LIMIT_PRICE = "limit_price";
    public static final String NOTIFICATION_REF_ID = "notification_ref_id";
    public static final String SUB_TYPE = "sub_type";
    public static final String TRIGGERED_AT = "triggered_at";
    public static final String TRIGGERED_AT_PRICE = "triggered_at_price";
    public static final String TRIGGERED_AT_TYPE = "triggered_at_type";
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String TRIGGER_TYPE = "trigger_type";
    public static final String GTTS = "gtts";
    public static final String PENDING = "pending";
    public static final String TRIGGERED = "triggered";
    public static final String COUNT = "count";
    public static final String GTT_INSTRUMENT_TYPE = "instrument_type";
    public static final String LOT_SIZE = "lot_size";
    public static final String TICK_SIZE = "tick_size";
    public static final String TAG_TYPE = "tag_type";
    public static final String TAG_ID = "tag_id";
    public static final String ALGO_MODULE = "algo_module";
    public static final String MERCHANT_ID = "merchant_id";
    public static final String CHANNEL_ID = "channel_id";
    public static final String MODE = "mode";
    public static final String PREF = "pref";
    public static final String TYPE = "type";

    public static final String FORWARD_SLASH = "/";
    public static final String COMMA = ",";
    public static final String COLON = ":";

}
