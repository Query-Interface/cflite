package com.queryinterface.cflite.cloudcontroller.processes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.queryinterface.cflite.cloudcontroller.apps.Application;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Processes define the runnable units of an app. An app can have multiple process types,
 * each with differing commands and scale. Processes for an app are defined by the buildpack
 * used to stage the app and can be customized by including a Procfile in the application source.
 * see: https://v3-apidocs.cloudfoundry.org/version/3.83.0/index.html#the-process-object
 */
@Entity
public class Process {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    private String type = "web";
    private String command;
    private int instances = 1;
    @JsonProperty("memory_in_mb")
    private int memory = 256;
    @JsonProperty("disk_in_mb")
    private int disk = 1024;
    @CreationTimestamp
    private ZonedDateTime created_At;
    @UpdateTimestamp
    private ZonedDateTime updated_At;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Application application;

    public Process() {
        // JPA
    }

    public Process(final String processType) {
        this.type = processType;
    }

    @JsonProperty("guid")
    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreated_At() {
        return created_At;
    }

    public ZonedDateTime getUpdated_At() {
        return updated_At;
    }

    public Application getApplication() {
        return application;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
