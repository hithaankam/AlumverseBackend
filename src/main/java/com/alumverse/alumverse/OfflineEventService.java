package com.alumverse.alumverse;

import org.springframework.stereotype.Service;

@Service
public class OfflineEventService implements BookEventService {
    @Override
    public void confirmRegistration(String name) {
        System.out.println("In-person Event");
        System.out.println("Confirm registration: " + name);
    }
}
