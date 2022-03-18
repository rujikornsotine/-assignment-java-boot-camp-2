package com.example.cashcenter.cashbox.gateway.dto;

public class QRGenerateRespones {
    private String qrID;
    private String QRCode;

    public String getQrID() {
        return qrID;
    }

    public void setQrID(String qrID) {
        this.qrID = qrID;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }
}
