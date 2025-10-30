package com.slab2.event.repository;

import com.slab2.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDate(java.time.LocalDate date);
    List<Event> findByLocationContainingIgnoreCase(String location);
    List<Event> findByTitleContainingIgnoreCase(String title);
}
