package com.queryinterface.cflite.cloudcontroller.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class JobDTO {

    private final Job job;
    private final boolean finished;

    public JobDTO(final Job job) {
        this(job, false);
    }

    public JobDTO(final Job job, boolean isFinished) {
        this.job = job;
        this.finished = isFinished;
    }

    @JsonProperty("entity")
    public Map<String, String> getEntity() {
        HashMap<String, String> map = new HashMap<>(2);
        map.put("guid", finished ? "0" : job.getId().toString());
        map.put("status", finished ? "finished" : job.getStatus());
        return map;
    }

    @JsonProperty("metadata")
    public Map<String, String> getMetadata() {
        HashMap<String, String> map = new HashMap<>(3);
        map.put("guid", finished ? "0" : job.getId().toString());
        map.put("created_at", finished ? "1970-01-01T00:00:00Z" : job.getCreatedAt().format(DateTimeFormatter.ISO_INSTANT));
        map.put("url", finished ? "/v2/jobs/0" : job.getUrl());
        return map;
    }
}
