package com.example.cashcenter.location;

import com.example.cashcenter.location.gateway.LocationGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTestSuccess {
    @Mock
    LocationGateway locationGateway;

    @Test
    @DisplayName("ทดสอบ service getlocationByIoTID เรียกแล้วเจอข้อมูล")
    void getlocationByIoTID() throws Exception {
        String url = "https://62318c2d05f5f4d40d7ce0f5.mockapi.io/location/";
        String urlGateway = "https://62318c2d05f5f4d40d7ce0f5.mockapi.io/location/1";
        Location location = new Location();
        location.setId("1");
        location.setLatitude("123456");
        location.setLongitude("7891011");

        Mockito.when(locationGateway.GetLocation(urlGateway)).thenReturn(location);

        LocationService locationService = new LocationService();
        locationService.setGateway(locationGateway);
        locationService.setUrl(url);

        Location location1 =  locationService.getlocationByIoTID("1");

        assertTrue(location1 != null);
        assertEquals(location1.getLatitude(),"123456");
        assertEquals(location1.getLongitude(),"7891011");
    }


}