package com.choice.config;

import com.choice.service.HotelClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfigClient {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.choice.wsdl");
        return marshaller;
    }
    @Bean
    public HotelClientService hotelClientservice(Jaxb2Marshaller marshaller) {
        HotelClientService client = new HotelClientService();
        client.setDefaultUri("http://localhost:9090/soapws/hotels.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
