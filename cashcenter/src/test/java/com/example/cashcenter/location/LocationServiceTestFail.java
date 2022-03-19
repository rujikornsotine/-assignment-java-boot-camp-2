package com.example.cashcenter.location;

import com.example.cashcenter.location.exception.LocationException;
import com.example.cashcenter.location.gateway.LocationGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class LocationServiceTestFail {
    @Mock
    LocationGateway locationGateway;

    @Test
    void getlocationByIoTID() throws Exception {

        String url = "https://62318c2d05f5f4d40d7ce0f5.mockapi.io/location/";
        String urlGateway = "https://62318c2d05f5f4d40d7ce0f5.mockapi.io/location/1";
        Mockito.when(locationGateway.GetLocation(urlGateway)).thenThrow(new Exception("Not found"));

        LocationService locationService = new LocationService();
        locationService.setGateway(locationGateway);
        locationService.setUrl(url);

        Exception exception = assertThrows(LocationException.class, () -> {
            Location location1 =  locationService.getlocationByIoTID("1");
        });

        String expectedMessage = "GetLocation error : Not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage,actualMessage);

    }
}