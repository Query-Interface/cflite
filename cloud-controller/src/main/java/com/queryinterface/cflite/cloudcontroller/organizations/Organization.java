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
    private UUID uuid;
    private String name;
    private boolean suspended = false;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Map<String, InfoLink> links = new HashMap<>();
    private MetaData metadata = new MetaData();
    private Map<String, ToOneRelationShip> relationships = new HashMap<>();

    public Organization(final String name) {
        this.name = name;
        uuid = UUID.randomUUID();
        created_at = LocalDateTime.of(LocalDate.of(2020, 4, 27), LocalTime.of(12, 0));
        updated_at = LocalDateTime.of(LocalDate.of(2020, 4, 28), LocalTime.of(12, 0));
        links.put("self", new InfoLink("http://localhost:8080/api/v3/organizations/"+uuid.toString()));
        links.put("domains", new InfoLink("http://localhost:8080/api/v3/organizations/"+uuid.toString()+"/domains"));
        links.put("default_domain", new InfoLink("http://localhost:8080/api/v3/organizations/"+uuid.toString()+"/domains/default"));
        links.put("quota", new InfoLink("http://localhost:8080/api/v3/organization_quotas/"+uuid.toString()));
        relationships.put("quota", new ToOneRelationShip(UUID.randomUUID().toString()));
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Map<String, InfoLink> getLinks() {
        return links;
    }

    public void setLinks(Map<String, InfoLink> links) {
        this.links = links;
    }
}
