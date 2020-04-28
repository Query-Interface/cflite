package com.queryinterface.cflite.cloudcontroller.spaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v3/spaces")
public class SpacesController {

    @RequestMapping(method = RequestMethod.GET, path = "spaces")
    public void getSpaces() {

    }
}
