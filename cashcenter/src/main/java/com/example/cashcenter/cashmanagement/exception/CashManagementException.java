package com.example.cashcenter.cashmanagement.exception;

import com.example.cashcenter.cashbox.exception.CashBoxException;
import com.example.cashcenter.exception.BaseException;

public class CashManagementException extends BaseException {
    public CashManagementException(String message, String errorcode) {
        super(message, errorcode);
    }
    public static CashManagementException ohterException(String message, String errorcode){
        return new CashManagementException(message,errorcode);
    }

    public static CashManagementException cashNotFound(){
        return new CashManagementException("cash not found.","001");
    }

    public static CashManagementException invalidParameter(String param){
        return new CashManagementException(String.format("Invalid Parameter %s",param),"002");
    }

}
