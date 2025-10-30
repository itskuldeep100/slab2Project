package com.slab2.jobportal.repository;

import com.slab2.jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCase(String title, String company);
}
