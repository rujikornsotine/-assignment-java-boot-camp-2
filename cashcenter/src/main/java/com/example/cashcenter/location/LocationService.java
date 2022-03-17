package com.example.cashcenter.location;

import com.example.cashcenter.location.exception.LocationException;
import com.example.cashcenter.location.gateway.LocationGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    @Value("${location.url}")
    private String url;

    private static final Logger log
            = LoggerFactory.getLogger(LocationService.class);
    @Autowired
    LocationGateway gateway;

    public Location getlocationByIoTID(String IoTID) throws LocationException {

        url = url + IoTID;
        Location location = new Location();
        try {
            location = gateway.GetLocation(url);

        } catch (Exception ex) {
            log.error("RestTemplate error : " +  ex.getMessage(), ex);
            throw LocationException.ohterException("RestTemplate error : " + ex.getMessage(), "99999");
        }

        return location;
    }

}
