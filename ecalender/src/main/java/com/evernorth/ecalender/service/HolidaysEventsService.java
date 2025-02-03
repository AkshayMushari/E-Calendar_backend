package com.evernorth.ecalender.service;

import com.evernorth.ecalender.entity.HolidaysEvents;
import com.evernorth.ecalender.repository.HolidaysEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidaysEventsService {

    @Autowired
    private HolidaysEventsRepository holidaysEventsRepository;

    public List<HolidaysEvents> getAllHolidaysEvents() {
        return holidaysEventsRepository.findAll();
    }

    public HolidaysEvents getHolidaysEventById(Integer id) {
        return holidaysEventsRepository.findById(id).orElse(null);
    }

    public HolidaysEvents saveHolidaysEvent(HolidaysEvents holidaysEvents) {
        return holidaysEventsRepository.save(holidaysEvents);
    }

    public HolidaysEvents updateHolidaysEvent(Integer id, HolidaysEvents holidaysEventDetails) {
        Optional<HolidaysEvents> optionalHolidaysEvent = holidaysEventsRepository.findById(id);
        if (optionalHolidaysEvent.isPresent()) {
            HolidaysEvents holidaysEvent = optionalHolidaysEvent.get();
            holidaysEvent.setOrganizer(holidaysEventDetails.getOrganizer());
            holidaysEvent.setEventName(holidaysEventDetails.getEventName());
            holidaysEvent.setEventDate(holidaysEventDetails.getEventDate());
            holidaysEvent.setEventTime(holidaysEventDetails.getEventTime());
            return holidaysEventsRepository.save(holidaysEvent);
        }
        return null;
    }

    public void deleteHolidaysEvent(Integer id) {
        holidaysEventsRepository.deleteById(id);
    }
}
