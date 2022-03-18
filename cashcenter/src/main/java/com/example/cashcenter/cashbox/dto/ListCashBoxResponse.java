package com.example.cashcenter.cashbox.dto;

import com.example.cashcenter.cashbox.CashBox;

import java.util.ArrayList;
import java.util.List;

public class ListCashBoxResponse {
    List<CashBoxResponse> cashBoxResponseList;

    public List<CashBoxResponse> getCashBoxResponseList() {
        return cashBoxResponseList;
    }

    public ListCashBoxResponse(List<CashBox> cashBoxes) {
        cashBoxResponseList = new ArrayList<CashBoxResponse>();
        for (var item : cashBoxes){
            cashBoxResponseList.add(new CashBoxResponse(item));
        }
    }
}
