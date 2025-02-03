package com.evernorth.ecalender.controller;

import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.service.HolidaysEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HolidaysEventsController {

    @Autowired
    private HolidaysEventsService holidaysEventsService;

    @GetMapping("/holidays-events")  // GET /holidays-events
    public List<HolidaysEvents> getAllHolidaysEvents() {
        return holidaysEventsService.getAllHolidaysEvents();
    }

    @GetMapping("/holidays-events/{id}")  // GET /holidays-events/{id}
    public HolidaysEvents getHolidaysEventById(@PathVariable Integer id) {
        return holidaysEventsService.getHolidaysEventById(id);
    }

    @PostMapping("/holidays-events")  // POST /holidays-events
    public HolidaysEvents createHolidaysEvent(@RequestBody HolidaysEvents holidaysEvents) {
        return holidaysEventsService.saveHolidaysEvent(holidaysEvents);
    }

    @PutMapping("/holidays-events/{id}")  // PUT /holidays-events/{id}
    public HolidaysEvents updateHolidaysEvent(@PathVariable Integer id, @RequestBody HolidaysEvents holidaysEvents) {
        return holidaysEventsService.updateHolidaysEvent(id, holidaysEvents);
    }

    @DeleteMapping("/holidays-events/{id}")  // DELETE /holidays-events/{id}
    public void deleteHolidaysEvent(@PathVariable Integer id) {
        holidaysEventsService.deleteHolidaysEvent(id);
    }
}
