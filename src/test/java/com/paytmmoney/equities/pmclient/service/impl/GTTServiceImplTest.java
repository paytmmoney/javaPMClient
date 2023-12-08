package com.paytmmoney.equities.pmclient.service.impl;

import com.paytmmoney.equities.pmclient.exception.ApplicationException;
import com.paytmmoney.equities.pmclient.model.SessionManager;
import com.paytmmoney.equities.pmclient.request.GTTOrderReqDto;
import com.paytmmoney.equities.pmclient.request.GTTOrderV2ReqDto;
import com.paytmmoney.equities.pmclient.request.GTTTransactionDetailsReqDTO;
import com.paytmmoney.equities.pmclient.request.GTTTransactionDetailsV2ReqDTO;
import com.paytmmoney.equities.pmclient.response.GTTAggregateDataResDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateResDto;
import com.paytmmoney.equities.pmclient.response.GTTAggregateStatusResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllDataResDTO;
import com.paytmmoney.equities.pmclient.response.GTTGetAllDataV2ResDTO;
import com.paytmmoney.equities.pmclient.response.GTTGetAllResDto;
import com.paytmmoney.equities.pmclient.response.GTTGetAllV2ResDto;
import com.paytmmoney.equities.pmclient.response.GTTMetaResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderDataResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderDataTransactionResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderDataTransactionV2ResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderDataV2ResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderResDto;
import com.paytmmoney.equities.pmclient.response.GTTOrderV2ResDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GTTServiceImplTest {

    MockedConstruction<RestTemplate> mocked;
    RestTemplate restTemplate;
    GTTServiceImpl gttServiceImpl;

    SessionManager sessionManager;

    @Before
    public void setUp() {
        mocked = Mockito.mockConstruction(RestTemplate.class);
        gttServiceImpl = new GTTServiceImpl();
        restTemplate = mocked.constructed().get(0);
        sessionManager = new SessionManager("1", "1","1","1","1");
    }

    @After
    public void initMock() {
        mocked.close();
    }

    private GTTOrderResDto getGTTOrderResDTO () {
        GTTOrderDataTransactionResDto gttOrderDataTransactionResDto = GTTOrderDataTransactionResDto.builder()
                .executionRefId("executionRefId").limitPrice(Double.valueOf(0.0d)).notificationRefId("notificationRefId")
                .quantity(Integer.valueOf(0)).subType("subType").triggerPrice(Double.valueOf(0.0d)).triggeredAt("triggeredAt")
                .triggeredAtPrice(Double.valueOf(0.0d)).triggeredAtType("triggeredAtType").build();
        List<GTTOrderDataTransactionResDto> gttOrderDataTransactionResDtoList = new ArrayList<>();
        gttOrderDataTransactionResDtoList.add(gttOrderDataTransactionResDto);
        GTTOrderDataResDto gttOrderDataResDto = GTTOrderDataResDto.builder().id("id").instructionId("instructionId")
                .segment("segment").exchange("exchange").securityId("securityId").pmlId("pmlId").name("name").userId("name")
                .status("status").transactionType("transactionType").transactionDetails(gttOrderDataTransactionResDtoList)
                .setPrice("setPrice").orderType("orderType").productType("productType").triggerType("triggerType")
                .cancellationCode("cancellationCode").cancellationReason("cancellationReason").expiryDate("expiryDate")
                .createdAt("createdAt").updatedAt("updatedAt").deletedAt("deletedAt").requestMetaData("requestMetaData").build();
        GTTMetaResDto gttMetaResDto = GTTMetaResDto.builder().displayMessage("displayMessage").status("status").build();
        return GTTOrderResDto.builder().data(gttOrderDataResDto).meta(gttMetaResDto).build();
    }

    private GTTOrderV2ResDto getGTTOrderV2ResDTO () {
        GTTOrderDataTransactionV2ResDto gttOrderDataTransactionV2ResDto = GTTOrderDataTransactionV2ResDto.builder()
                .executionRefId("executionRefId").limitPrice(Double.valueOf(0.0d)).notificationRefId("notificationRefId")
                .quantity(Integer.valueOf(0)).subType("subType").triggerPrice(Double.valueOf(0.0d)).triggeredAt("triggeredAt")
                .triggeredAtPrice(Double.valueOf(0.0d)).triggeredAtType("triggeredAtType").status("status").setPrice("setPrice")
                .orderType("orderType").build();
        List<GTTOrderDataTransactionV2ResDto> gttOrderDataTransactionV2ResDtoList = new ArrayList<>();
        gttOrderDataTransactionV2ResDtoList.add(gttOrderDataTransactionV2ResDto);
        GTTOrderDataV2ResDto gttOrderDataV2ResDto = GTTOrderDataV2ResDto.builder().id("id").instructionId("instructionId")
                .segment("segment").exchange("exchange").securityId("securityId").pmlId("pmlId").name("name").userId("name")
                .transactionType("transactionType").transactionDetails(gttOrderDataTransactionV2ResDtoList)
                .productType("productType").triggerType("triggerType").cancellationCode("cancellationCode")
                .cancellationReason("cancellationReason").expiryDate("expiryDate").createdAt("createdAt")
                .updatedAt("updatedAt").deletedAt("deletedAt").requestMetaData("requestMetaData").build();
        GTTMetaResDto gttMetaResDto = GTTMetaResDto.builder().displayMessage("displayMessage").status("status").build();
        return GTTOrderV2ResDto.builder().data(gttOrderDataV2ResDto).meta(gttMetaResDto).build();
    }

    @Test
    public void testCreateGTT() throws Exception {
        GTTOrderReqDto gttOrderReqDto = new GTTOrderReqDto("exchange", "orderType", "pmlId", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(response);
        GTTOrderResDto result = gttServiceImpl.createGTT(sessionManager, gttOrderReqDto);
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testCreateGTTException() throws Exception {
        GTTOrderReqDto gttOrderReqDto = new GTTOrderReqDto("exchange", "orderType", "pmlId", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.createGTT(sessionManager, gttOrderReqDto);
    }

    @Test
    public void testGetGTT() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(response);
        GTTOrderResDto result = gttServiceImpl.getGTT(sessionManager, "id");
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testGetGTTException() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getGTT(sessionManager, "id");
    }

    @Test
    public void testUpdateGTT() throws Exception {
        GTTOrderReqDto gttOrderReqDto = new GTTOrderReqDto("exchange", "orderType", "pmlId", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(response);
        GTTOrderResDto result = gttServiceImpl.updateGTT(sessionManager, "id", gttOrderReqDto);
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testUpdateGTTException() throws Exception {
        GTTOrderReqDto gttOrderReqDto = new GTTOrderReqDto("exchange", "orderType", "pmlId", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsReqDTO>asList(new GTTTransactionDetailsReqDTO(Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.updateGTT(sessionManager, "id", gttOrderReqDto);
    }

    @Test
    public void testDeleteGTT() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(response);
        GTTOrderResDto result = gttServiceImpl.deleteGTT(sessionManager, "id");
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testDeleteGTTException() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.deleteGTT(sessionManager, "id");
    }

    @Test
    public void testGetAllGTT() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        List<GTTOrderDataResDto> gttOrderDataResDtos = new ArrayList<>();
        gttOrderDataResDtos.add(gttOrderResDto.getData());
        GTTGetAllDataResDTO gttGetAllDataResDTO = GTTGetAllDataResDTO.builder().gtts(gttOrderDataResDtos).build();
        GTTGetAllResDto gttGetAllResDto = GTTGetAllResDto.builder().data(gttGetAllDataResDTO).build();
        ResponseEntity<GTTGetAllResDto> response = new ResponseEntity<GTTGetAllResDto>(gttGetAllResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTGetAllResDto>>any())
        ).thenReturn(response);
        GTTGetAllResDto result = gttServiceImpl.getAllGTT(sessionManager,"pml_id", "status");
        Assert.assertEquals(result.getData().getGtts().get(0).getId(),"id");
    }

    @Test(expected = ApplicationException.class)
    public void testGetAllGTTException() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        List<GTTOrderDataResDto> gttOrderDataResDtos = new ArrayList<>();
        gttOrderDataResDtos.add(gttOrderResDto.getData());
        GTTGetAllDataResDTO gttGetAllDataResDTO = GTTGetAllDataResDTO.builder().gtts(gttOrderDataResDtos).build();
        GTTGetAllResDto gttGetAllResDto = GTTGetAllResDto.builder().data(gttGetAllDataResDTO).build();
        ResponseEntity<GTTGetAllResDto> response = new ResponseEntity<GTTGetAllResDto>(gttGetAllResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTGetAllResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getAllGTT(sessionManager,"pml_id", "status");
    }

    @Test
    public void testGetGTTAggregate() throws Exception {
        List<GTTAggregateStatusResDto> gttAggregateStatusResDtos = new ArrayList<>();
        GTTAggregateStatusResDto gttAggregateStatusResDto = GTTAggregateStatusResDto.builder().name("name").pmlId("pmlId")
                .count("count").securityId("securityId").exchange("exchange").instrumentType("instrumentType").segment("segment")
                .lotSize("lotSize").tickSize("tickSize").build();
        gttAggregateStatusResDtos.add(gttAggregateStatusResDto);
        GTTAggregateDataResDto gttAggregateDataResDto = GTTAggregateDataResDto.builder().pending(gttAggregateStatusResDtos)
                .triggered(gttAggregateStatusResDtos).build();
        GTTMetaResDto gttMetaResDto = GTTMetaResDto.builder().displayMessage("displayMessage").status("status").build();
        GTTAggregateResDto gttAggregateResDto = GTTAggregateResDto.builder().data(gttAggregateDataResDto).meta(gttMetaResDto).build();
        ResponseEntity<GTTAggregateResDto> response = new ResponseEntity<GTTAggregateResDto>(gttAggregateResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTAggregateResDto>>any())
        ).thenReturn(response);
        GTTAggregateResDto result = gttServiceImpl.getGTTAggregate(sessionManager);
        Assert.assertEquals(result.getData().getPending().get(0).getName(), "name");
    }

    @Test(expected = ApplicationException.class)
    public void testGetGTTAggregateException() throws Exception {
        List<GTTAggregateStatusResDto> gttAggregateStatusResDtos = new ArrayList<>();
        GTTAggregateStatusResDto gttAggregateStatusResDto = GTTAggregateStatusResDto.builder().name("name").pmlId("pmlId")
                .count("count").securityId("securityId").exchange("exchange").instrumentType("instrumentType").segment("segment")
                .lotSize("lotSize").tickSize("tickSize").build();
        gttAggregateStatusResDtos.add(gttAggregateStatusResDto);
        GTTAggregateDataResDto gttAggregateDataResDto = GTTAggregateDataResDto.builder().pending(gttAggregateStatusResDtos)
                .triggered(gttAggregateStatusResDtos).build();
        GTTMetaResDto gttMetaResDto = GTTMetaResDto.builder().displayMessage("displayMessage").status("status").build();
        GTTAggregateResDto gttAggregateResDto = GTTAggregateResDto.builder().data(gttAggregateDataResDto).meta(gttMetaResDto).build();
        ResponseEntity<GTTAggregateResDto> response = new ResponseEntity<GTTAggregateResDto>(gttAggregateResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTAggregateResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getGTTAggregate(sessionManager);
    }

    @Test
    public void testGetGTTExpiry() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(response);
        GTTOrderResDto result = gttServiceImpl.getGTTExpiry(sessionManager, "pmlId");
        Assert.assertEquals(result.getData().getExpiryDate(),"expiryDate");
    }

    @Test(expected = ApplicationException.class)
    public void testGetGTTExpiryException() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getGTTExpiry(sessionManager, "pmlId");
    }

    @Test
    public void testGetGTTByInstructionId() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(response);
        GTTOrderResDto result = gttServiceImpl.getGTTByInstructionId(sessionManager, "id");
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testGetGTTByInstructionIdException() throws Exception {
        GTTOrderResDto gttOrderResDto = getGTTOrderResDTO();
        ResponseEntity<GTTOrderResDto> response = new ResponseEntity<GTTOrderResDto>(gttOrderResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getGTTByInstructionId(sessionManager, "id");
    }

    @Test
    public void testCreateGTTV2() throws Exception {
        GTTOrderV2ReqDto gttOrderV2ReqDto = new GTTOrderV2ReqDto("exchange", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsV2ReqDTO>asList(new GTTTransactionDetailsV2ReqDTO("id","subType","orderType",Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(response);
        GTTOrderV2ResDto result = gttServiceImpl.createGTTV2(sessionManager, gttOrderV2ReqDto);
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testCreateGTTV2Exception() throws Exception {
        GTTOrderV2ReqDto gttOrderV2ReqDto = new GTTOrderV2ReqDto("exchange", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsV2ReqDTO>asList(new GTTTransactionDetailsV2ReqDTO("id","subType","orderType",Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.createGTTV2(sessionManager, gttOrderV2ReqDto);
    }

    @Test
    public void testGetGTTV2() throws Exception {
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(response);
        GTTOrderV2ResDto result = gttServiceImpl.getGTTV2(sessionManager, "id");
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testGetGTTV2Exception() throws Exception {
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getGTTV2(sessionManager, "id");
    }

    @Test
    public void testUpdateGTTV2() throws Exception {
        GTTOrderV2ReqDto gttOrderV2ReqDto = new GTTOrderV2ReqDto("exchange", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsV2ReqDTO>asList(new GTTTransactionDetailsV2ReqDTO("id","subType","orderType",Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(response);
        GTTOrderV2ResDto result = gttServiceImpl.updateGTTV2(sessionManager, "id", gttOrderV2ReqDto);
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testUpdateGTTV2Exception() throws Exception {
        GTTOrderV2ReqDto gttOrderV2ReqDto = new GTTOrderV2ReqDto("exchange", "productType", "securityId", "segment", "setPrice", Arrays.<GTTTransactionDetailsV2ReqDTO>asList(new GTTTransactionDetailsV2ReqDTO("id","subType","orderType",Double.valueOf(0.0d), Integer.valueOf(0), Double.valueOf(0.0d))), "transactionType", "triggerType");
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.updateGTTV2(sessionManager, "id", gttOrderV2ReqDto);
    }

    @Test
    public void testGetAllGTTV2() throws Exception {
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        List<GTTOrderDataV2ResDto> gttOrderDataV2ResDtos = new ArrayList<>();
        gttOrderDataV2ResDtos.add(gttOrderV2ResDto.getData());
        GTTGetAllDataV2ResDTO gttGetAllDataV2ResDTO = GTTGetAllDataV2ResDTO.builder().gtts(gttOrderDataV2ResDtos).build();
        GTTGetAllV2ResDto gttGetAllV2ResDto = GTTGetAllV2ResDto.builder().data(gttGetAllDataV2ResDTO).build();
        ResponseEntity<GTTGetAllV2ResDto> response = new ResponseEntity<GTTGetAllV2ResDto>(gttGetAllV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTGetAllV2ResDto>>any())
        ).thenReturn(response);
        GTTGetAllV2ResDto result = gttServiceImpl.getAllGTTV2(sessionManager,"pml_id", "status");
        Assert.assertEquals(result.getData().getGtts().get(0).getId(),"id");
    }

    @Test(expected = ApplicationException.class)
    public void testGetAllGTTV2Exception() throws Exception {
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        List<GTTOrderDataV2ResDto> gttOrderDataV2ResDtos = new ArrayList<>();
        gttOrderDataV2ResDtos.add(gttOrderV2ResDto.getData());
        GTTGetAllDataV2ResDTO gttGetAllDataV2ResDTO = GTTGetAllDataV2ResDTO.builder().gtts(gttOrderDataV2ResDtos).build();
        GTTGetAllV2ResDto gttGetAllV2ResDto = GTTGetAllV2ResDto.builder().data(gttGetAllDataV2ResDTO).build();
        ResponseEntity<GTTGetAllV2ResDto> response = new ResponseEntity<GTTGetAllV2ResDto>(gttGetAllV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTGetAllV2ResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getAllGTTV2(sessionManager,"pml_id", "status");
    }

    @Test
    public void testGetGTTByInstructionIdV2() throws Exception {
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(response);
        GTTOrderV2ResDto result = gttServiceImpl.getGTTByInstructionIdV2(sessionManager, "id");
        Assert.assertEquals(result.getData().getId(), "id");
    }

    @Test(expected = ApplicationException.class)
    public void testGetGTTByInstructionIdV2Exception() throws Exception {
        GTTOrderV2ResDto gttOrderV2ResDto = getGTTOrderV2ResDTO();
        ResponseEntity<GTTOrderV2ResDto> response = new ResponseEntity<GTTOrderV2ResDto>(gttOrderV2ResDto, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GTTOrderV2ResDto>>any())
        ).thenReturn(null);
        gttServiceImpl.getGTTByInstructionIdV2(sessionManager, "id");
    }
}

