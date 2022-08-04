package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.GTTTransactionDetailsReqDTO;
import com.paytmmoney.equities.pmclient.response.GTTAggregateDataResDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateResDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateStatusResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllDataResDTO;
import com.paytmmoney.equities.pmclient.response.GTTGetAllResDto;
import com.paytmmoney.equities.pmclient.response.GTTMetaResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderDataResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderDataTransactionResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderResDto;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
class GTTServiceImplTest {

    MockedConstruction<RestTemplate> mocked;
    RestTemplate restTemplate;
    GTTServiceImpl gttServiceImpl;

    SessionManager sessionManager;

    public void setUp() {
        mocked = Mockito.mockConstruction(RestTemplate.class);
        gttServiceImpl = new GTTServiceImpl();
        restTemplate = mocked.constructed().get(0);
        sessionManager = new SessionManager("1", "1", "1");
    }

    @After
    public void initMock() {
        mocked.close();
    }

    @Test
    void testCreateGTT() throws Exception {
        GTTOrderResDto result = gttServiceImpl.createGTT(new SessionManager(null, null, "accessToken"), new GTTOrderReqDto("exchange", "orderType", "pmlId", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0), Integer.valueOf(0), Double.valueOf(0))), "transactionType", "triggerType"));
        Assertions.assertEquals(new GTTOrderResDto(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testGetGTT() throws Exception {
        GTTOrderResDto result = gttServiceImpl.getGTT(new SessionManager(null, null, "accessToken"), "id");
        Assertions.assertEquals(new GTTOrderResDto(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testUpdateGTT() throws Exception {
        GTTOrderResDto result = gttServiceImpl.updateGTT(new SessionManager(null, null, "accessToken"), "id", new GTTOrderReqDto("exchange", "orderType", "pmlId", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0), Integer.valueOf(0), Double.valueOf(0))), "transactionType", "triggerType"));
        Assertions.assertEquals(new GTTOrderResDto(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testDeleteGTT() throws Exception {
        GTTOrderResDto result = gttServiceImpl.deleteGTT(new SessionManager(null, null, "accessToken"), "id");
        Assertions.assertEquals(new GTTOrderResDto(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testGetAllGTT() throws Exception {
        GTTGetAllResDto result = gttServiceImpl.getAllGTT(new SessionManager(null, null, "accessToken"), "pml_id", "status");
        Assertions.assertEquals(new GTTGetAllResDto(new GTTGetAllDataResDTO(Arrays.<GTTOrderDataResDto>asList(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"))), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testGetGTTAggregate() throws Exception {
        GTTAggregateResDto result = gttServiceImpl.getGTTAggregate(new SessionManager(null, null, "accessToken"));
        Assertions.assertEquals(new GTTAggregateResDto(new GTTAggregateDataResDto(Arrays.<GTTAggregateStatusResDto>asList(new GTTAggregateStatusResDto("name", "pmlId", "count", "securityId", "exchange", "instrumentType", "segment", "lotSize", "tickSize")), Arrays.<GTTAggregateStatusResDto>asList(new GTTAggregateStatusResDto("name", "pmlId", "count", "securityId", "exchange", "instrumentType", "segment", "lotSize", "tickSize"))), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testGetGTTExpiry() throws Exception {
        GTTOrderResDto result = gttServiceImpl.getGTTExpiry(new SessionManager(null, null, "accessToken"), "pmlId");
        Assertions.assertEquals(new GTTOrderResDto(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"), new GTTMetaResDto("status", "displayMessage")), result);
    }

    @Test
    void testGetGTTByInstructionId() throws Exception {
        GTTOrderResDto result = gttServiceImpl.getGTTByInstructionId(new SessionManager(null, null, "accessToken"), "id");
        Assertions.assertEquals(new GTTOrderResDto(new GTTOrderDataResDto("id", "instructionId", "segment", "exchange", "securityId", "pmlId", "name", "userId", "status", "transactionType", Arrays.<GTTOrderDataTransactionResDto>asList(new GTTOrderDataTransactionResDto("executionRefId", Double.valueOf(0), "notificationRefId", Integer.valueOf(0), "subType", Double.valueOf(0), "triggeredAt", Double.valueOf(0), "triggeredAtType")), "setPrice", "orderType", "triggerType", "productType", "cancellationCode", "cancellationReason", "expiryDate", "createdAt", "updatedAt", "deletedAt", "requestMetaData"), new GTTMetaResDto("status", "displayMessage")), result);
    }
}

