package com.queryinterface.cflite.cloudcontroller.organizations;

import com.queryinterface.cflite.cloudcontroller.common.InfoLink;
import com.queryinterface.cflite.cloudcontroller.common.MetaData;
import com.queryinterface.cflite.cloudcontroller.common.ToOneRelationShip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Organization {
    private UUID guid;
    private String name;
    private boolean suspended = false;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Map<String, InfoLink> links = new HashMap<>();
    private MetaData metadata = new MetaData();
    private Map<String, ToOneRelationShip> relationships = new HashMap<>();

    public Organization(final String name) {
        this.name = name;
        guid = UUID.randomUUID();
        created_at = LocalDateTime.of(LocalDate.of(2020, 4, 27), LocalTime.of(12, 0));
        updated_at = LocalDateTime.of(LocalDate.of(2020, 4, 28), LocalTime.of(12, 0));
        links.put("self", new InfoLink("http://localhost:8080/api/v3/organizations/"+ guid.toString()));
        links.put("domains", new InfoLink("http://localhost:8080/api/v3/organizations/"+ guid.toString()+"/domains"));
        links.put("default_domain", new InfoLink("http://localhost:8080/api/v3/organizations/"+ guid.toString()+"/domains/default"));
        links.put("quota", new InfoLink("http://localhost:8080/api/v3/organization_quotas/"+ guid.toString()));
        relationships.put("quota", new ToOneRelationShip(UUID.randomUUID().toString()));
    }

    public UUID getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public Map<String, InfoLink> getLinks() {
        return links;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public Map<String, ToOneRelationShip> getRelationships() {
        return relationships;
    }
}
