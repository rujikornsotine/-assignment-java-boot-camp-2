package com.example.cashcenter.location;

import com.example.cashcenter.exception.ErrorResponse;
import com.example.cashcenter.location.dto.LocationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationControllerTestFail {

    @Autowired
    TestRestTemplate testRestTemplate;

    private final String Url = "/api/location/get/";

    @Test
    @DisplayName("ทดสอบการเรียก Controller getlocation fail")
    void getlocationFail() {
        String id = "2";
        ErrorResponse respones = testRestTemplate.getForObject(Url + id, ErrorResponse.class);
        assertEquals(respones.getErrorCode(),"99999");
    }

}