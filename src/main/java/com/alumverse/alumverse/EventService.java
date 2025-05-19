package com.alumverse.alumverse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private BookEventService bookEventService;
    public EventService(BookEventService bookEventService) {
        this.bookEventService = bookEventService;
    }
    public void register() {
        //var eventService = new OnlineEventService();
        bookEventService.confirmRegistration("Alice");

    }
}
