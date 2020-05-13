package com.queryinterface.cflite.cloudcontroller.spaces;

import com.queryinterface.cflite.cloudcontroller.common.IterableUtils;
import com.queryinterface.cflite.cloudcontroller.common.PaginatedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v3")
public class SpacesV3Controller {

    @Autowired
    private SpaceRepository spaceRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/spaces")
    public ResponseEntity<PaginatedResult<Space>> getSpaces(@RequestParam(required = false) String organization_guids, @RequestParam(required = false, name = "order-by") String orderBy, @RequestParam(required = false, name="q") String[] filter) {
        if (organization_guids != null) {
            Iterable<Space> spaces = spaceRepository.findAllByOrganizationId(UUID.fromString(organization_guids));
            return ResponseEntity.ok(new PaginatedResult<>("spaces", IterableUtils.toList(spaces)));
        } else if (filter != null) {
            if (filter[0].startsWith("organization_guid")) {
                final String guid = filter[0].substring(filter[0].indexOf(':'));
                return ResponseEntity.ok(new PaginatedResult<>("spaces", IterableUtils.of(spaceRepository.findById(UUID.fromString(guid)).get())));
            }
        }
        return ResponseEntity.ok(new PaginatedResult<>("spaces", Collections.emptyList()));
    }
}
