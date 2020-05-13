package com.queryinterface.cflite.cloudcontroller.common;

import com.queryinterface.cflite.cloudcontroller.apps.Application;
import com.queryinterface.cflite.cloudcontroller.jobs.Job;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Resource {

    private Map<String, String> metadata = new HashMap<>();

    private Map<String, Object> entity = new HashMap<>();

    public Resource() {

    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Map<String, Object> getEntity() {
        return entity;
    }

    public void setEntity(Map<String, Object> entity) {
        this.entity = entity;
    }

    public void addMetaDataProperty(final String name, final String value) {
        this.metadata.put(name, value);
    }

    public void addEntityProperty(final String name, final Object value) {
        this.entity.put(name, value);
    }

    public static Resource of(final Application app) {
        Resource resource = new Resource();
        resource.addMetaDataProperty("guid", app.getId().toString());
        resource.addMetaDataProperty("url", "/v2/apps/" + app.getId().toString());
        resource.addMetaDataProperty("created_at", app.getCreatedAt().format(DateTimeFormatter.ISO_INSTANT));
        resource.addMetaDataProperty("updated_at", app.getUpdatedAt().format(DateTimeFormatter.ISO_INSTANT));

        resource.addEntityProperty("name", app.getName());
        resource.addEntityProperty("space_guid", app.getSpace_guid());
        resource.addEntityProperty("buildpack", null);
        resource.addEntityProperty("detected_buildpack", null);
        resource.addEntityProperty("detected_buildpack_guid", null);
        resource.addEntityProperty("memory", app.getMemory());
        resource.addEntityProperty("instances", app.getInstances());
        resource.addEntityProperty("disk_quota", app.getDisk_quota());
        resource.addEntityProperty("state", app.getState());
        return resource;
    }

    public static Resource of(final Job job) {
        Resource resource = new Resource();
        resource.addMetaDataProperty("guid", job.getId().toString());
        resource.addMetaDataProperty("created_at", job.getCreatedAt().format(DateTimeFormatter.ISO_INSTANT));
        resource.addMetaDataProperty("url", job.getUrl());

        resource.addEntityProperty("guid", job.getId().toString());
        resource.addEntityProperty("status", job.getStatus());
        return resource;
    }
}
