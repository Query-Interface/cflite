package com.queryinterface.cflite.cloudcontroller.organizations;

import com.queryinterface.cflite.cloudcontroller.common.IterableUtils;
import com.queryinterface.cflite.cloudcontroller.common.PaginatedResult;
import com.queryinterface.cflite.cloudcontroller.uaa.User;
import com.queryinterface.cflite.cloudcontroller.uaa.UserNotFoundException;
import com.queryinterface.cflite.cloudcontroller.uaa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v3")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path="organizations")
    public ResponseEntity<PaginatedResult<Organization>> getOrganizations(Principal principal) {
        final Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        Iterable<Organization> organizations = organizationRepository.findAllByUserId(optionalUser.get().getId());
        return ResponseEntity.ok(new PaginatedResult<>("organizations", IterableUtils.toList(organizations)));
    }
}
