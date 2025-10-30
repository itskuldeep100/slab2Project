package com.slab2.event.repository;

import com.slab2.event.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    int countByEventId(Long eventId);
    List<Registration> findByUserName(String userName);
}
