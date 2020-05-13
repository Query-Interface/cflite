package com.queryinterface.cflite.cloudcontroller.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v2")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/jobs/{guid}")
    public ResponseEntity<JobDTO> getJob(@PathVariable String guid) {
        var job = getJobByGuid(guid);

        return ResponseEntity.ok(new JobDTO(job, job.getStatus().equals("queued")));
    }

    private Job getJobByGuid(final String guid) {
        final Optional<Job> optionalJob = jobRepository.findById(UUID.fromString(guid));
        if (optionalJob.isEmpty()) {
            throw new RuntimeException("Job not found");
        }
        return optionalJob.get();
    }
}
