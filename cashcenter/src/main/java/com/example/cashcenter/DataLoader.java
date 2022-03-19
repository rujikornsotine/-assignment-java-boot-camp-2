package com.example.cashcenter;

import com.example.cashcenter.cashbox.CashBox;
import com.example.cashcenter.cashbox.CashBoxRepository;
import com.example.cashcenter.cashbox.enums.CashBoxStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CashBoxRepository cashBoxRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SetDataCashBox();
    }

    private void SetDataCashBox(){
        LocalDateTime  dateTime = LocalDateTime.now();
        CashBox cashBox = new CashBox();
        cashBox.setDeliveryID("D0001");
        cashBox.setQrCodeID("QR001");
        cashBox.setEmpID("EMP001");
        cashBox.setAmount(1000.00);
        cashBox.setCurrencyCode("THB");
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCreateBy("test");
        cashBox.setCreateDate(dateTime);
        cashBoxRepository.save(cashBox);

        CashBox cashBox2 = new CashBox();
        cashBox2.setDeliveryID("D0001");
        cashBox2.setQrCodeID("QR001");
        cashBox2.setEmpID("EMP001");
        cashBox2.setAmount(1000.00);
        cashBox2.setCurrencyCode("THB");
        cashBox2.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox2.setCreateBy("test");
        cashBox2.setCreateDate(dateTime);
        cashBoxRepository.save(cashBox2);
    }
}
