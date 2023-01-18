package com.choice;

import com.choice.config.AppConfig;
import com.choice.service.HotelService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        HotelService service = applicationContext.getBean("hotelService", HotelService.class);
        System.out.println(service.list());
    }
}
