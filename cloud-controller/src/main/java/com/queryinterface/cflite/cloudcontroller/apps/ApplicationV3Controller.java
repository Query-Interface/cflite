package com.queryinterface.cflite.cloudcontroller.apps;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v3")
public class ApplicationV3Controller {

    @RequestMapping(method = RequestMethod.GET, path = "/apps/{guid}/droplets/current")
    public ResponseEntity getDroplet(final @PathVariable String guid) {
        // TODO
        return ResponseEntity.ok().build();
    }
}
