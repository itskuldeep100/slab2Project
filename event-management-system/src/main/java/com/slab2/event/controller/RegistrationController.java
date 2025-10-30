package com.slab2.event.controller;

import com.slab2.event.model.Registration;
import com.slab2.event.model.Event;
import com.slab2.event.repository.RegistrationRepository;
import com.slab2.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public Object registerForEvent(@RequestParam Long eventId, @RequestParam String userName) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        int currentRegistered = registrationRepository.countByEventId(eventId);
        if (currentRegistered >= event.getCapacity()) {
            return "Event is full";
        }
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUserName(userName);
        registration.setRegistrationDate(LocalDate.now());
        return registrationRepository.save(registration);
    }

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @GetMapping("/byevent/{eventId}")
    public List<Registration> getByEvent(@PathVariable Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    @GetMapping("/byuser/{userName}")
    public List<Registration> getByUser(@PathVariable String userName) {
        return registrationRepository.findByUserName(userName);
    }

    @DeleteMapping("/{id}")
    public void cancelRegistration(@PathVariable Long id) {
        registrationRepository.deleteById(id);
    }
}
