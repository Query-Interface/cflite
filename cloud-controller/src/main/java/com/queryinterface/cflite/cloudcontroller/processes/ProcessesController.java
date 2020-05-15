package com.queryinterface.cflite.cloudcontroller.processes;

import com.queryinterface.cflite.cloudcontroller.common.ResourceNotFoundException;
import com.queryinterface.cflite.cloudcontroller.common.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v3")
public class ProcessesController {

    @Autowired
    ProcessRepository processRepository;

    @RequestMapping(path = "/processes/{guid}/stats")
    public ResponseEntity getStats(final @PathVariable String guid) {
        Optional<Process> optionalProcess = processRepository.findById(UUID.fromString(guid));
        if (optionalProcess.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        final Stats stats = new Stats(optionalProcess.get().getType(), 0);
        final Resources<Stats> resources = new Resources<>(stats);
        return ResponseEntity.ok(resources);
    }
}
