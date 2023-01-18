package com.choice.config;

import com.choice.repository.HotelRepository;
import com.choice.service.HotelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.choice")
public class AppConfig {

//    @Bean("hotelService")
//    public HotelService getHotelService() {
//        return new HotelService(getHotelRepository());
//    }
//
//    @Bean("hotelRepository")
//    public HotelRepository getHotelRepository() {
//        return new HotelRepository();
//    }
//    }
}
