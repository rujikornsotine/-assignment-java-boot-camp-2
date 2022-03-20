package com.example.cashcenter.cashbox;

import com.example.cashcenter.cashbox.dto.CashBoxRequest;
import com.example.cashcenter.cashbox.dto.CashBoxResponse;
import com.example.cashcenter.cashbox.dto.ListCashBoxResponse;
import com.example.cashcenter.cashbox.exception.CashBoxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/cashbox")
public class CashBoxController {
    @Autowired
    CashBoxService cashBoxService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CashBoxResponse> getCashBoxByID(@PathVariable int id) throws CashBoxException {

        CashBox cashBox = cashBoxService.getCashBoxByID(id);
        CashBoxResponse response = new CashBoxResponse(cashBox);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/deliveryid/{id}")
    public ResponseEntity<ListCashBoxResponse> getByDeliveryID(@PathVariable String id) throws CashBoxException {

        List<CashBox> cashBoxes = cashBoxService.getCastBoxByDeliveryID(id);
        ListCashBoxResponse response = new ListCashBoxResponse(cashBoxes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/createdate/{startDate}/{endDate}")
    public ResponseEntity<ListCashBoxResponse> getByCreateDatetime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws CashBoxException {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay();
        List<CashBox> cashBoxes = cashBoxService.getCastBoxByCreateDatetime(start, end);
        ListCashBoxResponse response = new ListCashBoxResponse(cashBoxes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/empid/{id}")
    public ResponseEntity<ListCashBoxResponse> getCastBoxByEmpID(@PathVariable String id) throws CashBoxException {

        List<CashBox> cashBoxes = cashBoxService.getCastBoxByEmpID(id);
        ListCashBoxResponse response = new ListCashBoxResponse(cashBoxes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CashBoxResponse> addCashBox(@RequestBody CashBoxRequest cashBoxRequest) throws CashBoxException {

        CashBox cashBox = cashBoxService.addCastBox(cashBoxRequest.getEmpID(), cashBoxRequest.getDeliveryID(), cashBoxRequest.getAmount(), cashBoxRequest.getCurrencyCode(), cashBoxRequest.getCreateBy());
        CashBoxResponse response = new CashBoxResponse(cashBox);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/update")
    public ResponseEntity<CashBoxResponse> updateCashBoxByID(@RequestBody CashBoxRequest cashBoxRequest) throws CashBoxException {

        CashBox cashBox = cashBoxService.updateCastBox(cashBoxRequest.getId(), cashBoxRequest.getStatus(), cashBoxRequest.getCreateBy());
        CashBoxResponse response = new CashBoxResponse(cashBox);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get/readqr/{value}")
    public ResponseEntity<CashBoxResponse> readQRCashBox(@PathVariable String value) throws CashBoxException {

        CashBox cashBox = cashBoxService.readQRCashBox(value);
        CashBoxResponse response = new CashBoxResponse(cashBox);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
