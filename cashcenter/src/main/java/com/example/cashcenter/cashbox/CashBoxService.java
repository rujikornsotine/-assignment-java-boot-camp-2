package com.example.cashcenter.cashbox;

import com.example.cashcenter.cashbox.enums.CashBoxStatus;
import com.example.cashcenter.cashbox.exception.CashBoxException;
import com.example.cashcenter.cashbox.gateway.QRGateway;
import com.example.cashcenter.cashbox.gateway.dto.QRGenerateRespones;
import com.example.cashcenter.location.LocationService;
import com.example.cashcenter.location.exception.LocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CashBoxService {
    private static final Logger log = LoggerFactory.getLogger(LocationService.class);
    @Autowired
    CashBoxRepository cashBoxRepository;
    @Autowired
    QRGateway qrGateway;

    public CashBox getCashBoxByID(int id) throws CashBoxException {
        Optional<CashBox> cashBox;

        try {

            cashBox = cashBoxRepository.findById(id);

        } catch (Exception ex) {
            log.error("getCashBoxByID Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("getCashBoxByID Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBox.isEmpty()) {
            throw CashBoxException.CashBoxNotFound();
        }

        return cashBox.get();

    }

    public List<CashBox> getCastBoxByDeliveryID(String deliveryID) throws CashBoxException {

        List<CashBox> cashBoxList = new ArrayList<CashBox>();
        if (deliveryID == null || deliveryID.isEmpty()) {
            throw CashBoxException.InvalidParameter("deliveryID");
        }

        try {

            Optional<List<CashBox>> cashBox = cashBoxRepository.findByDeliveryID(deliveryID);
            cashBoxList = cashBox.get();

        } catch (Exception ex) {
            log.error("getCastBoxByDeliveryID Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("getCastBoxByDeliveryID Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBoxList.isEmpty()) {
            throw CashBoxException.CashBoxNotFound();
        }

        return cashBoxList;

    }

    public List<CashBox> getCastBoxByCreateDatetime(LocalDateTime startDate, LocalDateTime endDate) throws CashBoxException {

        List<CashBox> cashBoxList = new ArrayList<CashBox>();
        if (startDate == null) {
            throw CashBoxException.InvalidParameter("startDate");
        }

        if (endDate == null) {
            throw CashBoxException.InvalidParameter("endDate");
        }

        try {

            Optional<List<CashBox>> cashBox = cashBoxRepository.findByCreateDateBetween(startDate, endDate);
            cashBoxList = cashBox.get();

        } catch (Exception ex) {
            log.error("getCastBoxByCreateDatetime Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("getCastBoxByCreateDatetime Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBoxList.isEmpty()) {
            throw CashBoxException.CashBoxNotFound();
        }

        return cashBoxList;

    }

    public List<CashBox> getCastBoxByEmpID(String empID) throws CashBoxException {

        List<CashBox> cashBoxList = new ArrayList<CashBox>();
        if (empID == null || empID.isEmpty()) {
            throw CashBoxException.InvalidParameter("empID");
        }

        try {

            Optional<List<CashBox>> cashBox = cashBoxRepository.findByEmpID(empID);
            cashBoxList = cashBox.get();

        } catch (Exception ex) {
            log.error("getCastBoxByEmpID Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("getCastBoxByEmpID Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBoxList.isEmpty()) {
            throw CashBoxException.CashBoxNotFound();
        }

        return cashBoxList;

    }

    public CashBox addCastBox(String empID, String deliveryID, double amount, String currencyCode, String createBy) throws CashBoxException {

        CashBox cashBox = new CashBox();
        if (empID == null || empID.isEmpty()) {
            throw CashBoxException.InvalidParameter("empID");
        }

        if (deliveryID == null || deliveryID.isEmpty()) {
            throw CashBoxException.InvalidParameter("deliveryID");
        }


        if (amount <= 0) {
            throw CashBoxException.InvalidParameter("amount");
        }

        if (currencyCode == null || currencyCode.isEmpty()) {
            throw CashBoxException.InvalidParameter("currencyCode");
        }



        //Create Entity
        LocalDateTime dateTime = LocalDateTime.now();
        cashBox.setEmpID(empID);
        cashBox.setDeliveryID(deliveryID);

        cashBox.setAmount(amount);
        cashBox.setStatus(CashBoxStatus.CASH_BOX_CREATE);
        cashBox.setCurrencyCode(currencyCode);
        cashBox.setCreateBy(createBy);
        cashBox.setCreateDate(dateTime);
        cashBox.setUpdateBy(createBy);
        cashBox.setUpdateDate(dateTime);

        try {

            QRGenerateRespones qr = qrGateway.generateQRCode("");
            cashBox.setQrCode(qr.getQRCode());
            cashBox.setQrCodeID(qr.getQrID());

        } catch (Exception ex) {
            log.error("generateQRCode qrGateway error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("generateQRCode qrGateway error : " + ex.getMessage(), "99999");
        }

        try {

             cashBox = cashBoxRepository.save(cashBox);

        } catch (Exception ex) {
            log.error("addCastBox Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("addCastBox Repository error : " + ex.getMessage(), "99999");
        }

        return cashBox;

    }

    public CashBox updateCastBox(int id, int status, String updateBy) throws CashBoxException {

        CashBox cashBox = new CashBox();
        if (id <= 0) {
            throw CashBoxException.InvalidParameter("id");
        }

        if (status <= 0) {
            throw CashBoxException.InvalidParameter("status");
        }



        Optional<CashBox> boxOptional;

        try {

            boxOptional = cashBoxRepository.findById(id);

        } catch (Exception ex) {
            log.error("addCastBox Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("addCastBox Repository error : " + ex.getMessage(), "99999");
        }

        if(boxOptional.isEmpty()){
            throw CashBoxException.CashBoxNotFound();
        }

        try {
            cashBox = boxOptional.get();
            cashBox.setUpdateBy(updateBy);
            cashBox.setStatus(convertIntToEnumCashBoxStatus(status));
            cashBox = cashBoxRepository.save(cashBox);

        } catch (Exception ex) {
            log.error("addCastBox Repository error : " + ex.getMessage(), ex);
            throw CashBoxException.ohterException("addCastBox Repository error : " + ex.getMessage(), "99999");
        }

        return cashBox;

    }

    public CashBox readQRCashBox(String qrCodeReader) throws CashBoxException{
        CashBox cashBox = new CashBox();
        int id = 0;
        if (qrCodeReader == null || qrCodeReader.isEmpty()) {
            throw CashBoxException.InvalidParameter("qrCodeReader");
        }

        if(qrCodeReader.contains(":")){
            String[] sp =  qrCodeReader.split(":");
            if(sp.length > 0 && sp[0] != null ){
                id = Integer.parseInt(sp[0]);
            }
        }

        cashBox = getCashBoxByID(id);

        return  cashBox;
    }

    private CashBoxStatus convertIntToEnumCashBoxStatus(int status){
        switch (status){
            case 0: return CashBoxStatus.CASH_BOX_CREATE;
            case 1: return CashBoxStatus.CASH_BOX_SEND;
            case 2: return CashBoxStatus.CASH_BOX_RECEIVE;
        }
        return CashBoxStatus.CASH_BOX_CREATE;
    }
}
