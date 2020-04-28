package com.queryinterface.cflite.cloudcontroller.organizations;

import com.queryinterface.cflite.cloudcontroller.common.PaginatedResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/v3")
public class OrganizationController {

    @RequestMapping(method = RequestMethod.GET, path="organizations")
    public ResponseEntity<PaginatedResult<Organization>> getOrganizations() {
        List<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization("query-interface"));
        return ResponseEntity.ok(new PaginatedResult<>("organizations", organizations));
    }
}
