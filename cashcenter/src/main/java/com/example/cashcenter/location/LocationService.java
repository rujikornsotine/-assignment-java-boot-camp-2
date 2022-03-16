package com.example.cashcenter.location;

import com.example.cashcenter.location.dto.LocationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    @Value("${location.url}")
    private String url;

    public Location getlocationByIoTID(String IoTID){

        url = url +IoTID;
        Location location = new Location();
        RestTemplate restTemplate = new RestTemplate();
        location = restTemplate.getForObject(url,Location.class);
        return location;
    }

}
