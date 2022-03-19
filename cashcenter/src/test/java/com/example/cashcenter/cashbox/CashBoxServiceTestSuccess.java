package com.example.cashcenter.cashbox;

import com.example.cashcenter.cashbox.enums.CashBoxStatus;
import com.example.cashcenter.cashbox.exception.CashBoxException;
import com.example.cashcenter.cashbox.gateway.QRGateway;
import com.example.cashcenter.cashbox.gateway.dto.QRGenerateRespones;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CashBoxServiceTestSuccess {
    @Mock
    QRGateway qrGateway;
    @Mock
    CashBoxRepository cashBoxRepository;

    @Test
    @DisplayName("ทดสอบการเรียก service getCashBoxByID")
    void getCashBoxByID() throws CashBoxException {

        LocalDateTime dateTime = LocalDateTime.now();
        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D0001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(1000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setId(1);

        Mockito.when(cashBoxRepository.findById(1)).thenReturn(Optional.of(cashBox));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        CashBox result = service.getCashBoxByID(1);

        assertEquals(result.getId(),1);


    }

    @Test
    @DisplayName("ทดสอบการเรียก service getCastBoxByDeliveryID")
    void getCastBoxByDeliveryID() throws CashBoxException {

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

        List<CashBox> list = new ArrayList<CashBox>();
        list.add(cashBox);

        Mockito.when(cashBoxRepository.findByDeliveryID(deliveryID)).thenReturn(Optional.of(list));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        List<CashBox> result = service.getCastBoxByDeliveryID(deliveryID);

        assertEquals(result.get(0).getDeliveryID(),deliveryID);

    }

    @Test
    @DisplayName("ทดสอบการเรียก service getCastBoxByCreateDatetime")
    void getCastBoxByCreateDatetime() throws CashBoxException {
        LocalDateTime dateTime = LocalDateTime.now();
        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D0001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(1000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setId(1);

        List<CashBox> list = new ArrayList<CashBox>();
        list.add(cashBox);

        Mockito.when(cashBoxRepository.findByCreateDateBetween(dateTime,dateTime)).thenReturn(Optional.of(list));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        List<CashBox> result = service.getCastBoxByCreateDatetime(dateTime,dateTime);

        assertEquals(result.get(0).getCreateDate(),dateTime);

    }

    @Test
    @DisplayName("ทดสอบการเรียก service getCastBoxByEmpID")
    void getCastBoxByEmpID() throws CashBoxException {
        LocalDateTime dateTime = LocalDateTime.now();
        String empID = "EMP001";
        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID(empID);
        cashBox.setAmount(1000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setId(1);

        List<CashBox> list = new ArrayList<CashBox>();
        list.add(cashBox);

        Mockito.when(cashBoxRepository.findByEmpID(empID)).thenReturn(Optional.of(list));

        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);

        List<CashBox> result = service.getCastBoxByEmpID(empID);

        assertEquals(result.get(0).getEmpID(),empID);
    }

    @Test
    @DisplayName("ทดสอบการเรียก service addCastBox")
    void addCastBox() throws Exception {

        QRGenerateRespones respones = new QRGenerateRespones();
        respones.setQrID("QR001");
        respones.setQRCode("MToxMDAwMC4wMDpUSEI=");

        LocalDateTime dateTime = LocalDateTime.now();

        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(2000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setId(1);

        Mockito.when(qrGateway.generateQRCode("1:2000.0")).thenReturn(respones);
        Mockito.when(cashBoxRepository.save(Mockito.any(CashBox.class))).thenReturn(cashBox);
        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);
        service.setQrGateway(qrGateway);

        CashBox result =  service.addCastBox("EMP001","D001",2000,"THB","test");

        assertEquals(result.getEmpID(),"EMP001");
        assertEquals(result.getDeliveryID(),"D001");
        assertEquals(result.getAmount(),2000);
        assertEquals(result.getCurrencyCode(),"THB");
        assertEquals(result.getId(),1);
        assertEquals(result.getQrCode(),"MToxMDAwMC4wMDpUSEI=");
        assertEquals(result.getCreateBy(),"test");

    }

    @Test
    @DisplayName("ทดสอบการเรียก service updateCastBox")
    void updateCastBox() throws CashBoxException {


        LocalDateTime dateTime = LocalDateTime.now();

        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(2000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_SEND);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setUpdateBy("test2");
        cashBox.setId(1);

        Mockito.when(cashBoxRepository.findById(1)).thenReturn(Optional.of(cashBox));
        Mockito.when(cashBoxRepository.save(Mockito.any(CashBox.class))).thenReturn(cashBox);
        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);



        CashBox result =  service.updateCastBox(1,1,"test2");

        assertEquals(result.getId(),1);
        assertEquals(result.getStatus(),CashBoxStatus.CASH_BOX_SEND);
        assertEquals(result.getUpdateBy(),"test2");
    }

    @Test
    @DisplayName("ทดสอบการเรียก service updateCastBox")
    void readQRCashBox() throws CashBoxException {
        LocalDateTime dateTime = LocalDateTime.now();

        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(2000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_SEND);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBox.setUpdateBy("test2");
        cashBox.setId(1);

        Mockito.when(cashBoxRepository.findById(1)).thenReturn(Optional.of(cashBox));
        CashBoxService service = new CashBoxService();
        service.setCashBoxRepository(cashBoxRepository);
        CashBox result = service.readQRCashBox("1:2000.0");
        assertEquals(result.getId(),1);

    }



}