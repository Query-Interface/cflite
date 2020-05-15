package com.queryinterface.cflite.cloudcontroller.apps;

import com.queryinterface.cflite.cloudcontroller.common.*;
import com.queryinterface.cflite.cloudcontroller.common.Error;
import com.queryinterface.cflite.cloudcontroller.processes.Process;
import com.queryinterface.cflite.cloudcontroller.processes.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/api/v3")
public class ApplicationV3Controller {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProcessRepository processRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/apps/{guid}/droplets/current")
    public ResponseEntity getDroplet(final @PathVariable String guid) {
        Error error = new Error(10010,"CF-ResourceNotFound");
        Errors errors = new Errors();
        errors.addError(error);
        return ResponseEntity.ok(errors);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/apps")
    public ResponseEntity<PaginatedResult<Application>> getApplications(final @RequestParam(required = false) String names, final @RequestParam(value = "space_guids", required = false) String spaces, final @RequestParam(required = false, defaultValue = "1") int page, final @RequestParam(required = false, defaultValue = "20") int per_page) {
        final List<String> applicationNames = new ArrayList<>();
        final List<UUID> spaceGuids = new ArrayList<>();
        if (names != null) {
            Stream.of(names.split(",")).forEach(applicationNames::add);
        }
        if (spaces != null) {
            Stream.of(spaces.split(",")).forEach(part -> spaceGuids.add(UUID.fromString(part)));
        }
        var applications = applicationRepository.findByNameInAndSpaceIdIn(applicationNames, spaceGuids);
        return ResponseEntity.ok(new PaginatedResult<Application>("apps", IterableUtils.toList(applications)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "apps/{guid}/processes")
    public ResponseEntity<PaginatedResult<Process>> getProcesses(@PathVariable("guid") final String applicationGuid) {
        Iterable<Process> processes = processRepository.findAllByApplicationId(UUID.fromString(applicationGuid));
        return ResponseEntity.ok(new PaginatedResult<>("processes", IterableUtils.toList(processes)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "apps/{guid}/processes/{type}")
    public ResponseEntity<Process> getProcess(@PathVariable final String guid, @PathVariable final String type) {
        Optional<Process> optionalProcess = processRepository.findFirstByApplicationIdAndType(UUID.fromString(guid), type);
        if (optionalProcess.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(optionalProcess.get());
    }
}
