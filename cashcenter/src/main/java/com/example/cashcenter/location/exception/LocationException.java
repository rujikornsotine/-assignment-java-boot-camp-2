package com.example.cashcenter.location.exception;

import com.example.cashcenter.exception.BaseException;

public class LocationException extends BaseException {
    public LocationException(String message, String errorcode) {
        super(message, errorcode);
    }

    public static LocationException locationNotFound(){
        return new LocationException("Location not found!","L0001");
    }

    public static LocationException ohterException(String message, String errorcode){
        return new LocationException(message,errorcode);
    }
}
