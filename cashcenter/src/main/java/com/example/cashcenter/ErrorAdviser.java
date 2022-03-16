package com.example.cashcenter;

import com.example.cashcenter.exception.BaseException;
import com.example.cashcenter.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorAdviser {
    public ErrorAdviser() {
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> HandlerBaseException(BaseException ex){
        ErrorResponse errorRespones = new ErrorResponse();
        errorRespones.setErrorMessage(ex.getMessage());
        errorRespones.setErrorCode(ex.errorcode);
        errorRespones.setStatus(HttpStatus.OK.value());
        return  new ResponseEntity<>(errorRespones,HttpStatus.OK);
    }
}
