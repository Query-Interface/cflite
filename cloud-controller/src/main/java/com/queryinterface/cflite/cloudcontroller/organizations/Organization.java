package com.queryinterface.cflite.cloudcontroller.organizations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.queryinterface.cflite.cloudcontroller.common.InfoLink;
import com.queryinterface.cflite.cloudcontroller.common.MetaData;
import com.queryinterface.cflite.cloudcontroller.common.ToOneRelationShip;
import com.queryinterface.cflite.cloudcontroller.uaa.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Organization {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private boolean suspended = false;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @Transient
    private Map<String, InfoLink> links = new HashMap<>();
    @Transient
    private MetaData metadata = new MetaData();
    @Transient
    private Map<String, ToOneRelationShip> relationships = new HashMap<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    Organization() {
        // for JPA
    }

    public Organization(final String name) {
        this.name = name;
        id = UUID.randomUUID();
        created_at = LocalDateTime.of(LocalDate.of(2020, 4, 27), LocalTime.of(12, 0));
        updated_at = LocalDateTime.of(LocalDate.of(2020, 4, 28), LocalTime.of(12, 0));
        links.put("self", new InfoLink("http://localhost:8080/api/v3/organizations/"+ id.toString()));
        links.put("domains", new InfoLink("http://localhost:8080/api/v3/organizations/"+ id.toString()+"/domains"));
        links.put("default_domain", new InfoLink("http://localhost:8080/api/v3/organizations/"+ id.toString()+"/domains/default"));
        links.put("quota", new InfoLink("http://localhost:8080/api/v3/organization_quotas/"+ id.toString()));
        relationships.put("quota", new ToOneRelationShip(UUID.randomUUID().toString()));
    }

    @JsonProperty("guid")
    public UUID getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
