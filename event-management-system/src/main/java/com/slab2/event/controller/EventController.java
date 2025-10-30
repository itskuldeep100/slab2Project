package com.slab2.event.controller;

import com.slab2.event.model.Event;
import com.slab2.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return eventRepository.findById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setTitle(updatedEvent.getTitle());
            event.setDescription(updatedEvent.getDescription());
            event.setDate(updatedEvent.getDate());
            event.setTime(updatedEvent.getTime());
            event.setLocation(updatedEvent.getLocation());
            event.setCapacity(updatedEvent.getCapacity());
            return eventRepository.save(event);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }

    @GetMapping("/search/title")
    public List<Event> searchByTitle(@RequestParam String q) {
        return eventRepository.findByTitleContainingIgnoreCase(q);
    }

    @GetMapping("/search/location")
    public List<Event> searchByLocation(@RequestParam String q) {
        return eventRepository.findByLocationContainingIgnoreCase(q);
    }

    @GetMapping("/search/date")
    public List<Event> searchByDate(@RequestParam String q) {
        LocalDate date = LocalDate.parse(q);
        return eventRepository.findByDate(date);
    }
}
