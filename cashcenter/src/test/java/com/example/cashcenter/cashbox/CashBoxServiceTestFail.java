package com.example.cashcenter.cashbox;

import com.example.cashcenter.cashbox.enums.CashBoxStatus;
import com.example.cashcenter.cashbox.exception.CashBoxException;
import com.example.cashcenter.cashbox.gateway.QRGateway;
import com.example.cashcenter.location.Location;
import com.example.cashcenter.location.exception.LocationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CashBoxServiceTestFail {
    @Mock
    CashBoxRepository cashBoxRepository;
    @Mock
    QRGateway qrGateway;


    //Fail Case getCashBoxByID
    //1. Repo error
    //2.getId empty

    @Test
    @DisplayName("Fail Case getCashBoxByID Repo error")
    void getCashBoxByIDCase1() throws Exception {

        Mockito.when(cashBoxRepository.findById(1)).thenThrow(new RuntimeException("test repo error"));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCashBoxByID(1);
        });

        String expectedMessage = "getCashBoxByID Repository error : test repo error";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    @DisplayName("Fail Case getCashBoxByID getId empty")
    void getCashBoxByIDCase2() throws CashBoxException {

        Mockito.when(cashBoxRepository.findById(1)).thenReturn(Optional.empty());

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCashBoxByID(1);
        });

        String expectedMessage = "CashBox not found.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }

    //Fail Case getCastBoxByDeliveryID
    //1. Invalid
    //2. Repo error
    //3.getId empty

    @Test
    @DisplayName("Fail Case getCastBoxByDeliveryID Invalid")

    void getCastBoxByDeliveryIDCase1(){

        CashBoxService service = new CashBoxService();


        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByDeliveryID("");
        });

        String expectedMessage = "Invalid Parameter deliveryID";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case getCastBoxByDeliveryID Repo error")
    void getCastBoxByDeliveryIDCase2() throws Exception {

        Mockito.when(cashBoxRepository.findByDeliveryID("D005")).thenThrow(new RuntimeException("test repo error"));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByDeliveryID("D005");
        });

        String expectedMessage = "getCastBoxByDeliveryID Repository error : test repo error";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    @DisplayName("Fail Case getCastBoxByDeliveryID getId empty")
    void getCastBoxByDeliveryIDCase3() throws CashBoxException {

        Mockito.when(cashBoxRepository.findByDeliveryID("005")).thenReturn(Optional.of(new ArrayList<>()));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByDeliveryID("005");
        });

        String expectedMessage = "CashBox not found.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }

    //Fail Case getCastBoxByCreateDatetime
    //1. Invalid
    //2. Repo error
    //3.getId empty
    @Test
    @DisplayName("Fail Case getCastBoxByCreateDatetime Invalid start date")
    void getCastBoxByCreateDatetimeCase1(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByCreateDatetime(null,LocalDateTime.now());
        });

        String expectedMessage = "Invalid Parameter startDate";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case getCastBoxByCreateDatetime Invalid end date")
    void getCastBoxByCreateDatetimeCase2(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByCreateDatetime(LocalDateTime.now(),null);
        });

        String expectedMessage = "Invalid Parameter endDate";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case getCastBoxByCreateDatetime Repo error")
    void getCastBoxByCreateDatetimeCase3(){
        LocalDateTime date = LocalDateTime.now();
        Mockito.when(cashBoxRepository.findByCreateDateBetween(date,date)).thenThrow(new RuntimeException("test repo error"));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByCreateDatetime(date,date);
        });

        String expectedMessage = "getCastBoxByCreateDatetime Repository error : test repo error";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case getCastBoxByCreateDatetime get empty")
    void getCastBoxByCreateDatetimeCase4(){
        LocalDateTime date = LocalDateTime.now();
        Mockito.when(cashBoxRepository.findByCreateDateBetween(date,date)).thenReturn(Optional.of(new ArrayList<>()));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByCreateDatetime(date,date);
        });

        String expectedMessage = "CashBox not found.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }


    //Fail Case getCastBoxByEmpID
    //1. Invalid
    //2. Repo error
    //3.getId empty
    @Test
    @DisplayName("Fail Case getCastBoxByEmpID Invalid empID")
    void getCastBoxByEmpIDCase1(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByEmpID("");
        });

        String expectedMessage = "Invalid Parameter empID";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case getCastBoxByEmpID Repo error")
    void getCastBoxByEmpIDCase2() throws Exception {

        Mockito.when(cashBoxRepository.findByEmpID("001")).thenThrow(new RuntimeException("test repo error"));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByEmpID("001");
        });

        String expectedMessage = "getCastBoxByEmpID Repository error : test repo error";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }

    @Test
    @DisplayName("Fail Case getCastBoxByEmpID empty")
    void getCastBoxByEmpIDCase3() throws Exception {

        Mockito.when(cashBoxRepository.findByEmpID("001")).thenReturn(Optional.of(new ArrayList<>()));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.getCastBoxByEmpID("001");
        });

        String expectedMessage = "CashBox not found.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }

    //Fail Case add
    //1. Invalid
    //2. Repo error
    //3. gateway error
    @Test
    @DisplayName("Fail Case add Invalid empID")
    void addInvalidCase1(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.addCastBox("","",0,"","");
        });

        String expectedMessage = "Invalid Parameter empID";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case add Invalid deliveryID")
    void addInvalidCase2(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.addCastBox("001","",0,"","");
        });

        String expectedMessage = "Invalid Parameter deliveryID";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case add Invalid currencyCode")
    void addInvalidCase3(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.addCastBox("001","002",1000,"THB","");
        });

        String expectedMessage = "Invalid Parameter currencyCode";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case add Repo error")
    void addRepoerror(){

        CashBoxService service = new CashBoxService();

        Mockito.when(cashBoxRepository.save(Mockito.any(CashBox.class))).thenThrow(new RuntimeException("test repo error"));
        service.setCashBoxRepository(cashBoxRepository);
        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.addCastBox("001","002",1000,"THB","test");
        });

        String expectedMessage = "addCastBox Repository error : test repo error";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case add Repo error")
    void addGatewayError() throws Exception {

        LocalDateTime dateTime = LocalDateTime.now();
        String deliveryID = "D0001";
        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID(deliveryID);
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(1000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setId(1);

        CashBoxService service = new CashBoxService();
        Mockito.when(qrGateway.generateQRCode("1:1000.0")).thenThrow(new Exception("Gateway error"));
        Mockito.when(cashBoxRepository.save(Mockito.any(CashBox.class))).thenReturn(cashBox);
        service.setCashBoxRepository(cashBoxRepository);
        service.setQrGateway(qrGateway);

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.addCastBox("001","002",1000,"THB","test");
        });

        String expectedMessage = "generateQRCode qrGateway error : Gateway error";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    //Fail Case edit
    //1. Invalid
    //2. Repo error
    //3. gateway error
    @Test
    @DisplayName("Fail Case update Invalid id")
    void updateInvalidCase1(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.updateCastBox(0,0,"");
        });

        String expectedMessage = "Invalid Parameter id";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case update Invalid status")
    void updateInvalidCase2(){

        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.updateCastBox(1,0,"");
        });

        String expectedMessage = "Invalid Parameter status";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    @DisplayName("Fail Case read qr Invalid qrCodeReader")
    void readQRFailInvalidCase1(){
        CashBoxService service = new CashBoxService();

        CashBoxException exception = assertThrows(CashBoxException.class, () -> {
            service.readQRCashBox("");
        });

        String expectedMessage = "Invalid Parameter qrCodeReader";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }


}