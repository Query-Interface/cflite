package com.queryinterface.cflite.buildpackservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BuildController
{

    @RequestMapping(method = RequestMethod.POST, path="/build")
    public ResponseEntity createImage(@RequestBody PushCommand pushCommand) {



        return ResponseEntity.ok().build();
    }
}
