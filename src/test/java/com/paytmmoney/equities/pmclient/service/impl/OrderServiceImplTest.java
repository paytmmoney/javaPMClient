//package com.paytmmoney.equities.pmclient.service.impl;
//
//import com.paytmmoney.equities.pmclient.enums.OrderProductType;
//import com.paytmmoney.equities.pmclient.exception.ApplicationException;
//import com.paytmmoney.equities.pmclient.model.SessionManager;
//import com.paytmmoney.equities.pmclient.request.ConvertOrderReqDto;
//import com.paytmmoney.equities.pmclient.request.EdisIsin;
//import com.paytmmoney.equities.pmclient.request.EdisValidateReqDto;
//import com.paytmmoney.equities.pmclient.request.OrderReqDto;
//import com.paytmmoney.equities.pmclient.response.EdisDataResDto;
//import com.paytmmoney.equities.pmclient.response.EdisDataStatusResDto;
//import com.paytmmoney.equities.pmclient.response.EdisIsinResDto;
//import com.paytmmoney.equities.pmclient.response.EdisParamResDto;
//import com.paytmmoney.equities.pmclient.response.EdisResDto;
//import com.paytmmoney.equities.pmclient.response.EdisStatusResDto;
//import com.paytmmoney.equities.pmclient.response.Meta;
//import com.paytmmoney.equities.pmclient.response.OrderDataResDto;
//import com.paytmmoney.equities.pmclient.response.OrderResDto;
//import com.paytmmoney.equities.pmclient.response.TpinGenerateResDto;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.MockedConstruction;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import org.testng.Assert;
//
//import java.util.Arrays;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OrderServiceImplTest {
//
//    MockedConstruction<RestTemplate> mocked;
//    RestTemplate restTemplate;
//    SessionManager sessionManager;
//    OrderServiceImpl orderServiceImpl;
//
//    @Before
//    public void setUp() {
//        mocked = Mockito.mockConstruction(RestTemplate.class);
//        orderServiceImpl = new OrderServiceImpl();
//        restTemplate = mocked.constructed().get(0);
//        sessionManager = new SessionManager("1", "1", "1");
//    }
//
//    @After
//    public void initMock() {
//        mocked.close();
//    }
//
//    @Test
//    public void testPlaceOrderRegular() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.INTRADAY.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.placeOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(), "orderNo");
//    }
//
//    @Test
//    public void testPlaceOrderBracket() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.BRACKET.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.placeOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(), "orderNo");
//    }
//
//    @Test
//    public void testPlaceOrderCover() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.COVER.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.placeOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(), "orderNo");
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testPlaceOrderException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.placeOrder(sessionManager, new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
//    }
//
//    @Test
//    public void testModifyOrderRegular() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.INTRADAY.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.modifyOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test
//    public void testModifyOrderBracket() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.BRACKET.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.modifyOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test
//    public void testModifyOrderCover() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.COVER.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.modifyOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testModifyOrderException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.modifyOrder(sessionManager, new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
//    }
//
//    @Test
//    public void testCancelOrder() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.INTRADAY.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.cancelOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test
//    public void testCancelOrderBracket() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.BRACKET.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.cancelOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test
//    public void testCancelOrderCover() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        OrderReqDto orderReqDto = new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        orderReqDto.setProduct(OrderProductType.COVER.getOrderType());
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.cancelOrder(sessionManager, orderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testCancelOrderException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.cancelOrder(sessionManager, new OrderReqDto(Double.valueOf(0), "mktType", "orderNo", Integer.valueOf(0), Integer.valueOf(0), "legNo", Double.valueOf(0), Double.valueOf(0), "algoOrderNo", "clientId", "transactionId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
//    }
//
//    @Test
//    public void testConvertOrder() throws Exception {
//        OrderResDto orderResDto = new OrderResDto(Arrays.<OrderDataResDto>asList(new OrderDataResDto("orderNo","","",1)), "error_code", "message", "status", "uuid");
//        ConvertOrderReqDto convertOrderReqDto = new ConvertOrderReqDto("exchange", "mktType", "productFrom", "productTo", Long.valueOf(1), "securityId", "segment", "source", "txnType", "transactionId", "clientId", "edisAuthMode", "edisAuthCode", null, "edisTxnId");
//        ResponseEntity<OrderResDto> response = new ResponseEntity<OrderResDto>(orderResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(response);
//        OrderResDto result = orderServiceImpl.convertOrder(sessionManager, convertOrderReqDto);
//        Assert.assertEquals(result.getData().get(0).getOrderNo(),"orderNo");
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testConvertOrderException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<OrderResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.convertOrder(sessionManager, new ConvertOrderReqDto("exchange", "mktType", "productFrom", "productTo", Long.valueOf(1), "securityId", "segment", "source", "txnType", "transactionId", "clientId", "edisAuthMode", "edisAuthCode", null, "edisTxnId"));
//    }
//
//    @Test
//    public void testGenerateEdisTpin() throws Exception {
//        TpinGenerateResDto tpinGenerateResDto = new TpinGenerateResDto(new EdisDataResDto(new EdisParamResDto("dPId", "reqId", "transDtls", "version"), "redirectionUrl", "edisRequestId", "tradingDate", Arrays.<EdisIsin>asList(new EdisIsin("isin", Long.valueOf(1), true))), new Meta("displayMessage"));
//        ResponseEntity<TpinGenerateResDto> response = new ResponseEntity<TpinGenerateResDto>(tpinGenerateResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<TpinGenerateResDto>>any())
//        ).thenReturn(response);
//        TpinGenerateResDto result = orderServiceImpl.generateEdisTpin(sessionManager);
//        Assert.assertEquals(result.getMeta().getDisplayMessage(), "displayMessage");
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testGenerateEdisTpinException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<TpinGenerateResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.generateEdisTpin(sessionManager);
//    }
//
//    @Test
//    public void testGetEdisStatus() throws Exception {
//        EdisStatusResDto edisResDto = new EdisStatusResDto(
//                new EdisDataStatusResDto( "edisRequestId", "tradingDate", Arrays.<EdisIsinResDto>asList(new EdisIsinResDto("isin", true))), new Meta("displayMessage"));
//        ResponseEntity<EdisStatusResDto> response = new ResponseEntity<>(edisResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<EdisStatusResDto>>any())
//        ).thenReturn(response);
//        EdisStatusResDto result = orderServiceImpl.getEdisStatus(sessionManager, "edisReqId");
//        Assert.assertEquals(result.getData().getEdisRequestId(),"edisRequestId");
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testGetEdisStatusException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<EdisResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.getEdisStatus(sessionManager,"edis_request_id");
//    }
//
//    @Test
//    public void testValidateEdisTpin() throws Exception {
//        EdisResDto edisResDto = new EdisResDto(new EdisDataResDto(new EdisParamResDto("dPId", "reqId", "transDtls", "version"), "redirectionUrl", "edisRequestId", "tradingDate", Arrays.<EdisIsin>asList(new EdisIsin("isin", Long.valueOf(1), true))), new Meta("displayMessage"));
//        EdisValidateReqDto edisValidateReqDto = new EdisValidateReqDto(Arrays.<EdisIsin>asList(new EdisIsin("isin", Long.valueOf(1), true)), "tradeType");
//        ResponseEntity<EdisResDto> response = new ResponseEntity<EdisResDto>(edisResDto, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<EdisResDto>>any())
//        ).thenReturn(response);
//        EdisResDto result = orderServiceImpl.validateEdisTpin(sessionManager, edisValidateReqDto);
//        Assert.assertEquals(result.getData().getEdisRequestId(),"edisRequestId" );
//    }
//
//    @Test(expected = ApplicationException.class)
//    public void testValidateEdisTpinException() throws Exception {
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<EdisResDto>>any())
//        ).thenReturn(null);
//        orderServiceImpl.validateEdisTpin(sessionManager,new EdisValidateReqDto(Arrays.<EdisIsin>asList(new EdisIsin("isin", Long.valueOf(1), true)), "tradeType"));
//    }
//}
