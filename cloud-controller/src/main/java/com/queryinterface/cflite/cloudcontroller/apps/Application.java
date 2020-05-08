package com.queryinterface.cflite.cloudcontroller.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.queryinterface.cflite.cloudcontroller.spaces.Space;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Application {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private int memory = 1024;
    private int instances = 1;
    private int disk_quota  = 1024;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "space_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Space space;

    public Application() {
        // for JPA
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("guid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public int getDisk_quota() {
        return disk_quota;
    }

    public void setDisk_quota(int disk_quota) {
        this.disk_quota = disk_quota;
    }

    @JsonProperty("space_guid")
    public UUID getSpace_guid() {
        return space.getId();
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
