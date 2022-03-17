package com.example.cashcenter.location;

import com.example.cashcenter.location.dto.LocationResponse;
import com.example.cashcenter.location.exception.LocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RequestMapping("api/location")
@RestController
public class LocationController {

    @Autowired
    private LocationService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<LocationResponse> getlocation(@PathVariable String id) throws LocationException {
        Location location = service.getlocationByIoTID(id);
        LocationResponse locationResponse = new  LocationResponse(location.getLongitude(),location.getLatitude());
        return new ResponseEntity<>(locationResponse, HttpStatus.OK);
    }




}
