package com.slab2.jobportal.repository;

import com.slab2.jobportal.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
