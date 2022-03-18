package com.example.cashcenter.cashbox.gateway;

import com.example.cashcenter.cashbox.gateway.dto.QRGenerateRespones;
import com.example.cashcenter.location.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QRGateway {
    private static final Logger log
            = LoggerFactory.getLogger("outbound-logs");
    @Value("${qr.url}")
    private String url;

    public QRGenerateRespones generateQRCode(String value) throws Exception {

        QRGenerateRespones respones = new QRGenerateRespones();
        log.info("generateQRCode Start !!!!!" );
        log.info("generateQRCode URL : " + url);

        RestTemplate restTemplate = new RestTemplate();
        try {

            //qrValue = restTemplate.getForObject(url, String.class);
            respones.setQrID("QR001");
            respones.setQRCode("MToxMDAwMC4wMDpUSEI=");
        }
        catch (Exception ex){
            log.error("QRGateway generateQRCode error.",ex);
            throw new  Exception("QRGateway generateQRCode error.",ex);
        }
        log.info("generateQRCode Return [ QRValue :" + respones.getQRCode() +"]");
        log.info("generateQRCode End !!!!!" );

        return respones;
    }

}
