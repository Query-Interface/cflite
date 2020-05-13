package com.queryinterface.cflite.cloudcontroller.spaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v2")
public class SpaceController {
    @RequestMapping(method = RequestMethod.GET, path = "/spaces/{guid}/summary")
    public ResponseEntity<Space> getSpace(final @PathVariable String guid) {
        // TODO
        return ResponseEntity.ok(new Space());
    }
}
