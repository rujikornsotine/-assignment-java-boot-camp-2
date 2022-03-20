package com.example.cashcenter.cashmanagement;

import com.example.cashcenter.cashmanagement.enums.CashManagementStatus;
import com.example.cashcenter.cashmanagement.exception.CashManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CashManagementService {

    private static final Logger log = LoggerFactory.getLogger(CashManagementService.class);
    @Autowired
    CashManagementRepository cashManagementRepository;

    public CashManagement getCashManagementByID(int id) throws CashManagementException {
        Optional<CashManagement> cashBox;

        try {

            cashBox = cashManagementRepository.findById(id);

        } catch (Exception ex) {
            log.error("getCashManagementByID Repository error : " + ex.getMessage(), ex);
            throw CashManagementException.ohterException("getCashManagementByID Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBox.isEmpty()) {
            throw CashManagementException.cashNotFound();
        }

        return cashBox.get();

    }

    public List<CashManagement> getCashManagementByCashIn() throws CashManagementException {
        Optional<List<CashManagement>> cashBoxes;

        try {

            cashBoxes = cashManagementRepository.findByStatus(CashManagementStatus.CASH_IN);

        } catch (Exception ex) {
            log.error("getCashManagementByCashin Repository error : " + ex.getMessage(), ex);
            throw CashManagementException.ohterException("getCashManagementByCashin Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBoxes.isEmpty()) {
            throw CashManagementException.cashNotFound();
        }

        return cashBoxes.get();

    }

    public List<CashManagement> getCashManagementByCashOut() throws CashManagementException {
        Optional<List<CashManagement>> cashBoxes;

        try {

            cashBoxes = cashManagementRepository.findByStatus(CashManagementStatus.CASH_OUT);

        } catch (Exception ex) {
            log.error("getCashManagementByCashout Repository error : " + ex.getMessage(), ex);
            throw CashManagementException.ohterException("getCashManagementByCashout Repository error : " + ex.getMessage(), "99999");
        }

        if (cashBoxes.isEmpty()) {
            throw CashManagementException.cashNotFound();
        }

        return cashBoxes.get();

    }

    public CashManagement addCash(String empID, String deliveryID, double amount, String currencyCode, String createBy) throws CashManagementException {

        CashManagement cash = new CashManagement();
        if (empID == null || empID.isEmpty()) {
            throw CashManagementException.invalidParameter("empID");
        }

        if (deliveryID == null || deliveryID.isEmpty()) {
            throw CashManagementException.invalidParameter("deliveryID");
        }

        if (amount <= 0) {
            throw CashManagementException.invalidParameter("amount");
        }

        if (currencyCode == null || currencyCode.isEmpty()) {
            throw CashManagementException.invalidParameter("currencyCode");
        }

        //Create Entity
        LocalDateTime dateTime = LocalDateTime.now();
        cash.setEmpID(empID);
        cash.setDeliveryID(deliveryID);
        cash.setAmount(amount);
        cash.setStatus(CashManagementStatus.CASH_IN);
        cash.setCurrencyCode(currencyCode);
        cash.setCreateBy(createBy);
        cash.setCreateDate(dateTime);
        cash.setUpdateBy(createBy);
        cash.setUpdateDate(dateTime);

        try {

            cash = cashManagementRepository.save(cash);

        } catch (Exception ex) {
            log.error("addCash Repository error : " + ex.getMessage(), ex);
            throw CashManagementException.ohterException("addCash Repository error : " + ex.getMessage(), "99999");
        }

        return cash;

    }

    public CashManagement updateCash(int id, int status, String updateBy) throws CashManagementException {

        CashManagement cashBox = new CashManagement();
        if (id <= 0) {
            throw CashManagementException.invalidParameter("id");
        }

        if (status <= 0) {
            throw CashManagementException.invalidParameter("status");
        }

        Optional<CashManagement> cashOptional;

        try {

            cashOptional = cashManagementRepository.findById(id);

        } catch (Exception ex) {
            log.error("updateCash Repository error : " + ex.getMessage(), ex);
            throw CashManagementException.ohterException("addCastBox Repository error : " + ex.getMessage(), "99999");
        }

        if (cashOptional.isEmpty()) {
            throw CashManagementException.cashNotFound();
        }

        try {
            cashBox = cashOptional.get();
            cashBox.setUpdateBy(updateBy);
            cashBox.setStatus(convertIntToEnumCashManagementStatus(status));
            cashBox = cashManagementRepository.save(cashBox);

        } catch (Exception ex) {
            log.error("updateCash Repository error : " + ex.getMessage(), ex);
            throw CashManagementException.ohterException("updateCash Repository error : " + ex.getMessage(), "99999");
        }

        return cashBox;

    }

    private CashManagementStatus convertIntToEnumCashManagementStatus(int status) {
        switch (status) {
            case 0:
                return CashManagementStatus.CASH_IN;
            case 1:
                return CashManagementStatus.CASH_OUT;

        }
        return CashManagementStatus.CASH_IN;
    }

}
