package com.example.cashcenter.location.gateway;

import com.example.cashcenter.location.Location;
import com.example.cashcenter.location.LocationService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LocationGateway {
    private static final Logger log
            = LoggerFactory.getLogger("outbound-logs");

    public Location GetLocation(String url) throws Exception {

        log.info("GetLocation Start !!!!!" );
        log.info("GetLocation URL : " + url);
        Location location = new Location();
        RestTemplate restTemplate = new RestTemplate();
        try {
            location = restTemplate.getForObject(url, Location.class);
        }
        catch (Exception ex){
            log.error("LocationGateway GetLocation error.",ex);
            throw new  Exception("LocationGateway GetLocation error.",ex);
        }
        log.info("GetLocation Return [ Latitude :" + location.getLatitude()+  ",Longitude : "  +location.getLongitude()+"]");
        log.info("GetLocation End !!!!!" );

        return location;

    }
}
