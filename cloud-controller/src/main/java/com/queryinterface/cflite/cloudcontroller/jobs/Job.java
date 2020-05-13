package com.queryinterface.cflite.cloudcontroller.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Job {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    private String status;
    private ZonedDateTime created_at = ZonedDateTime.now();

    public Job() {
        // JPA
    }

    private Job(UUID id, String status, ZonedDateTime created_at) {
        this.id = id;
        this.status = status;
        this.created_at = created_at;
    }

    public Job(final String status) {
        this.status = status;
    }

    @JsonProperty("guid")
    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @JsonProperty("url")
    public String getUrl() {
        return "v2/jobs/" + id.toString();
    }

    @JsonProperty("created_at")
    public ZonedDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(ZonedDateTime created_at) {
        this.created_at = created_at;
    }
}
