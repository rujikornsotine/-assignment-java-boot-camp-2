package com.example.cashcenter.cashbox;

import com.example.cashcenter.cashbox.dto.CashBoxRequest;
import com.example.cashcenter.cashbox.dto.CashBoxResponse;
import com.example.cashcenter.cashbox.dto.ListCashBoxResponse;
import com.example.cashcenter.cashbox.enums.CashBoxStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashBoxControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private final String Url = "/api/cashbox/get/";
    private final String UrlAdd = "/api/cashbox/add";
    private final String UrlUpdate = "/api/cashbox/update";

    @Test
    @DisplayName("ทดสอบการเรียก Controller getCashBoxByID")
    void getCashBoxByID() {
        int id = 1;
        CashBoxResponse respones = testRestTemplate.getForObject(Url + id, CashBoxResponse.class);
        assertEquals(respones.getId(),1);
    }

    @Test
    @DisplayName("ทดสอบการเรียก Controller getByDeliveryID")
    ///get/deliveryid/{id}
    void getByDeliveryID() {
        String  id = "D0001";
        ListCashBoxResponse respones = testRestTemplate.getForObject(Url + "deliveryid/"+id, ListCashBoxResponse.class);
        CashBoxResponse cashBox = respones.getCashBoxResponseList().stream().findFirst().get();
        assertEquals(cashBox.getDeliveryID(),id);
    }

    @Test
    @DisplayName("ทดสอบการเรียก Controller getByCreateDatetime")
    ///get/createdate/{startDate}/{endDate}
    void getByCreateDatetime() {
        String  start = "2022-03-19";
        String  end = "2022-03-20";
        ListCashBoxResponse respones = testRestTemplate.getForObject(Url + "createdate/"+start+"/"+end+"", ListCashBoxResponse.class);
        CashBoxResponse cashBox = respones.getCashBoxResponseList().stream().findFirst().get();
        assertEquals(cashBox.getCreateDate().toLocalDate(), LocalDate.now());
    }

    @Test
    @DisplayName("ทดสอบการเรียก Controller getCastBoxByEmpID")
    ///get/empid/{id}
    void getCastBoxByEmpID() {
        String  id = "EMP001";
        ListCashBoxResponse respones = testRestTemplate.getForObject(Url + "empid/"+id, ListCashBoxResponse.class);
        CashBoxResponse cashBox = respones.getCashBoxResponseList().stream().findFirst().get();
        assertEquals(cashBox.getEmpID(),id);
    }

    @Test
    @DisplayName("ทดสอบการเรียก Controller addCashBox")
    void addCashBox() {

        CashBoxRequest cashBoxRequest = new CashBoxRequest();
        cashBoxRequest.setDeliveryID("D0001");
        cashBoxRequest.setEmpID("EMP0003");
        cashBoxRequest.setAmount(1000);
        cashBoxRequest.setCurrencyCode("THB");
        cashBoxRequest.setCreateBy("test");

        ResponseEntity<CashBoxResponse> response = testRestTemplate.postForEntity(UrlAdd, cashBoxRequest, CashBoxResponse.class);
        CashBoxResponse cashBoxResponse = response.getBody();

        assertEquals(cashBoxResponse.getEmpID(),"EMP0003");
        assertEquals(cashBoxResponse.getDeliveryID(),"D0001");
        assertEquals(cashBoxResponse.getStatus(), CashBoxStatus.CASH_BOX_CREATE);
        assertEquals(cashBoxResponse.getQrCode(),"MToxMDAwMC4wMDpUSEI=");
    }

    @Test
    @DisplayName("ทดสอบการเรียก Controller updateCashBoxByID")
    void updateCashBoxByID() {

        CashBoxRequest cashBoxRequest = new CashBoxRequest();
        cashBoxRequest.setStatus(1);
        cashBoxRequest.setId(1);
        cashBoxRequest.setCreateBy("test2");

        ResponseEntity<CashBoxResponse> response = testRestTemplate.postForEntity(UrlUpdate, cashBoxRequest, CashBoxResponse.class);
        CashBoxResponse cashBoxResponse = response.getBody();
        assertEquals(cashBoxResponse.getStatus(), CashBoxStatus.CASH_BOX_SEND);
        assertEquals(cashBoxResponse.getUpdateBy(),"test2");
    }

    @Test
    @DisplayName("ทดสอบการเรียก Controller readQRCashBox")
    ///get/readqr/{value}
    void readQRCashBox() {
        String value = "2:10000";
        CashBoxResponse respones = testRestTemplate.getForObject(Url + "readqr/"+value, CashBoxResponse.class);
        assertEquals(respones.getEmpID(), "EMP001");
        assertEquals(respones.getDeliveryID(), "D0001");
        assertEquals(respones.getId(),2);
    }
}