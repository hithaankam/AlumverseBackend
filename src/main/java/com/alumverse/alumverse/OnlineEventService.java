package com.alumverse.alumverse;

import org.springframework.stereotype.Service;


public class OnlineEventService implements BookEventService {
    @Override
    public void confirmRegistration(String name){
        System.out.println("REMOTE EVENT");
        System.out.println("Confirmed Registration for "+name);
    }
}
