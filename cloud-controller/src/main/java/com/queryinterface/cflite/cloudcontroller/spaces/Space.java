package com.queryinterface.cflite.cloudcontroller.spaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.queryinterface.cflite.cloudcontroller.common.InfoLink;
import com.queryinterface.cflite.cloudcontroller.common.MetaData;
import com.queryinterface.cflite.cloudcontroller.common.ToOneRelationShip;
import com.queryinterface.cflite.cloudcontroller.organizations.Organization;
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
public class Space {
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
    @JoinColumn(name = "organization_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Organization organization;

    Space() {
        // for JPA
    }

    public Space(final String name) {
        this.name = name;
        created_at = LocalDateTime.of(LocalDate.of(2020, 4, 27), LocalTime.of(12, 0));
        updated_at = LocalDateTime.of(LocalDate.of(2020, 4, 28), LocalTime.of(12, 0));

        // TODO: add missing parameter method => see https://v3-apidocs.cloudfoundry.org/version/3.83.0/index.html#the-space-object
    }

    @JsonProperty("guid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
        links.put("self", new InfoLink("http://localhost:8080/api/v3/spaces/"+ id.toString()));
        links.put("features", new InfoLink("http://localhost:8080/api/v3/spaces/"+ id.toString()+"/features"));
        links.put("apply_manifest", new InfoLink("http://localhost:8080/api/v3/spaces/"+ id.toString()+"/actions/apply_manifest"));
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

    public Organization getOrganization() { return organization; }

    public void setOrganization(Organization organization) {
        this.organization = organization;
        links.put("organization", new InfoLink("http://localhost:8080/api/v3/organizations/"+ organization.getId().toString()));
        relationships.put("quota", new ToOneRelationShip(organization.getId().toString()));
    }
}
