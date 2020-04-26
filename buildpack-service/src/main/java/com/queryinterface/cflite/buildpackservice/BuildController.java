package com.queryinterface.cflite.buildpackservice;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> createImage(@RequestBody PushCommand pushCommand) {
        PackHelper helper = new PackHelper();
        try {
            helper.build(pushCommand);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
