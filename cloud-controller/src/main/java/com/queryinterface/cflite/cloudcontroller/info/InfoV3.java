package com.queryinterface.cflite.cloudcontroller.info;

import com.queryinterface.cflite.cloudcontroller.common.InfoLink;
import com.queryinterface.cflite.cloudcontroller.common.InfoLinkExperimental;

import java.util.HashMap;
import java.util.Map;

public class InfoV3 {
    private Map<String, InfoLink> links = new HashMap<String, InfoLink>();

    InfoV3() {
        links.put("self", new InfoLink("http://localhost:8080/api/v3"));
        links.put("apps", new InfoLink("http://localhost:8080/api/v3/apps"));
        links.put("audit_events", new InfoLink("http://localhost:8080/api/v3/audit_events"));
        links.put("buildpacks", new InfoLink("http://localhost:8080/api/v3/buildpacks"));
        links.put("builds", new InfoLink("http://localhost:8080/api/v3/builds"));
        links.put("deployments", new InfoLink("http://localhost:8080/api/v3/deployments"));
        links.put("domains", new InfoLink("http://localhost:8080/api/v3/domains"));
        links.put("droplets", new InfoLink("http://localhost:8080/api/v3/droplets"));
        links.put("environment_variable_groups", new InfoLink("http://localhost:8080/api/v3/environment_variable_groups"));
        links.put("feature_flags", new InfoLink("http://localhost:8080/api/v3/feature_flags"));
        links.put("info", new InfoLink("http://localhost:8080/api/v3/info"));
        links.put("isolation_segments", new InfoLink("http://localhost:8080/api/v3/isolation_segments"));
        links.put("organizations", new InfoLink("http://localhost:8080/api/v3/organizations"));
        links.put("organization_quotas", new InfoLinkExperimental("http://localhost:8080/api/v3/organization_quotas"));
        links.put("packages", new InfoLink("http://localhost:8080/api/v3/packages"));
        links.put("processes", new InfoLink("http://localhost:8080/api/v3/processes"));
        links.put("resource_matches", new InfoLink("http://localhost:8080/api/v3/resource_matches"));
        links.put("roles", new InfoLink("http://localhost:8080/api/v3/roles"));
        links.put("routes", new InfoLink("http://localhost:8080/api/v3/routes"));
        links.put("service_brokers", new InfoLinkExperimental("http://localhost:8080/api/v3/service_brokers"));
        links.put("service_instances", new InfoLink("http://localhost:8080/api/v3/service_instances"));
        links.put("service_offerings", new InfoLinkExperimental("http://localhost:8080/api/v3/service_offerings"));
        links.put("service_plans", new InfoLinkExperimental("http://localhost:8080/api/v3/service_plans"));
        links.put("spaces", new InfoLink("http://localhost:8080/api/v3/spaces"));
        links.put("space_quotas", new InfoLink("http://localhost:8080/api/v3/space_quotas"));
        links.put("stacks", new InfoLinkExperimental("http://localhost:8080/api/v3/stacks"));
        links.put("tasks", new InfoLink("http://localhost:8080/api/v3/tasks"));
        links.put("users", new InfoLink("http://localhost:8080/api/v3/users"));
    }

    public Map<String, InfoLink> getLinks() {
        return links;
    }
}
