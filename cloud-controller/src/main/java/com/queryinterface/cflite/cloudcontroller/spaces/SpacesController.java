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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v3")
public class SpacesController {

    @Autowired
    private SpaceRepository spaceRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/spaces")
    public ResponseEntity<PaginatedResult<Space>> getSpaces(@RequestParam String organization_guids) {
        Iterable<Space> spaces = spaceRepository.findAllByOrganizationId(UUID.fromString(organization_guids));
        return ResponseEntity.ok(new PaginatedResult<>("spaces", IterableUtils.toList(spaces)));
    }
}
