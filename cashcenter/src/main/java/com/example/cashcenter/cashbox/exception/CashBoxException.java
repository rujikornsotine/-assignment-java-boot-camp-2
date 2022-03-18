package com.example.cashcenter.cashbox.exception;

import com.example.cashcenter.exception.BaseException;
import com.example.cashcenter.location.exception.LocationException;

public class CashBoxException extends BaseException {


    public CashBoxException(String message, String errorcode) {
        super(message, errorcode);
    }

    public static CashBoxException ohterException(String message, String errorcode){
        return new CashBoxException(message,errorcode);
    }

    public static CashBoxException CashBoxNotFound(){
        return new CashBoxException("CashBox not found.","001");
    }

    public static CashBoxException InvalidParameter(String param){
        return new CashBoxException(String.format("Invalid Parameter %s",param),"002");
    }
}
