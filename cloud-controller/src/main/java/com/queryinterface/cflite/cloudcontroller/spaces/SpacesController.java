package com.queryinterface.cflite.cloudcontroller.spaces;

import com.queryinterface.cflite.cloudcontroller.common.PaginatedResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v3")
public class SpacesController {

    @RequestMapping(method = RequestMethod.GET, path = "spaces")
    public ResponseEntity<PaginatedResult<Space>> getSpaces() {
        List<Space> spaces = new ArrayList<>();
        spaces.add(new Space("dev"));
        return ResponseEntity.ok(new PaginatedResult<>("spaces", spaces));
    }
}
