package com.alumverse.alumverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AlumverseApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(AlumverseApplication.class, args);
        var eventService = context.getBean(EventService.class);
        //        var orderService = new EventService(new OnlineEventService());
        eventService.register();
//        var orderService1 = new EventService(new OfflineEventService());
//        orderService1.register();
    }

}
