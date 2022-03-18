package com.example.cashcenter.cashbox.dto;

import com.example.cashcenter.cashbox.CashBox;
import com.example.cashcenter.cashbox.enums.CashBoxStatus;

import java.time.LocalDateTime;

public class CashBoxResponse {

    private int id;
    private String deliveryID;
    private String qrCodeID;
    private String empID;
    private String qrCode;
    private double amount;
    private String currencyCode;
    private CashBoxStatus status;
    private String createBy;
    private LocalDateTime createDate;
    private String updateBy;
    private LocalDateTime updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getQrCodeID() {
        return qrCodeID;
    }

    public void setQrCodeID(String qrCodeID) {
        this.qrCodeID = qrCodeID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CashBoxStatus getStatus() {
        return status;
    }

    public void setStatus(CashBoxStatus status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public CashBoxResponse(CashBox cashBox) {
        this.id = cashBox.getId();
        this.deliveryID = cashBox.getDeliveryID();
        this.qrCodeID = cashBox.getQrCodeID();
        this.empID = cashBox.getEmpID();
        this.qrCode = cashBox.getQrCode();
        this.amount = cashBox.getAmount();
        this.currencyCode = cashBox.getCurrencyCode();
        this.status = cashBox.getStatus();
        this.createBy = cashBox.getCreateBy();
        this.createDate = cashBox.getCreateDate();
        this.updateBy = cashBox.getUpdateBy();
        this.updateDate = cashBox.getUpdateDate();
    }
}
