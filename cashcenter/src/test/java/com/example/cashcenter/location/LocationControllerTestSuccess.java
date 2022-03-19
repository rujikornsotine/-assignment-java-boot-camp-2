package com.example.cashcenter.location;

import com.example.cashcenter.location.dto.LocationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationControllerTestSuccess {

    @Autowired
    TestRestTemplate testRestTemplate;

    private final String Url = "/api/location/get/";

    @Test
    @DisplayName("ทดสอบการเรียก Controller getlocation")
    void getlocation() {
        String id = "1";
        LocationResponse respones = testRestTemplate.getForObject(Url + id, LocationResponse.class);
        assertEquals(respones.getLatitude(),"9876543213.22");
        assertEquals(respones.getLongitude(),"123465.22");
    }

}