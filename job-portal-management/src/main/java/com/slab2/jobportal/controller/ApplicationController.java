package com.slab2.jobportal.controller;

import com.slab2.jobportal.model.Application;
import com.slab2.jobportal.model.Job;
import com.slab2.jobportal.repository.ApplicationRepository;
import com.slab2.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private JobRepository jobRepository;

    @PostMapping
    public Application applyForJob(@RequestParam Long jobId, @RequestParam String applicantName) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        Application application = new Application();
        application.setJob(job);
        application.setApplicantName(applicantName);
        application.setAppliedDate(LocalDate.now());
        return applicationRepository.save(application);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @GetMapping("/byjob/{jobId}")
    public List<Application> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationRepository.findAll().stream()
            .filter(a -> a.getJob().getId().equals(jobId)).toList();
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationRepository.deleteById(id);
    }
}
