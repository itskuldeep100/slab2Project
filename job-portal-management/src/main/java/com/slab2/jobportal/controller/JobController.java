package com.slab2.jobportal.controller;

import com.slab2.jobportal.model.Job;
import com.slab2.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id);
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        return jobRepository.findById(id).map(job -> {
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setCompany(updatedJob.getCompany());
            job.setLocation(updatedJob.getLocation());
            job.setPostedDate(updatedJob.getPostedDate());
            return jobRepository.save(job);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
    }
    
    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String q) {
        return jobRepository.findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCase(q, q);
    }
}
