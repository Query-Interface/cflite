package com.queryinterface.cflite.cloudcontroller;

import com.queryinterface.cflite.cloudcontroller.apps.ApplicationRepository;
import com.queryinterface.cflite.cloudcontroller.organizations.Organization;
import com.queryinterface.cflite.cloudcontroller.organizations.OrganizationRepository;
import com.queryinterface.cflite.cloudcontroller.spaces.Space;
import com.queryinterface.cflite.cloudcontroller.spaces.SpaceRepository;
import com.queryinterface.cflite.cloudcontroller.uaa.User;
import com.queryinterface.cflite.cloudcontroller.uaa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DevDataCLR implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("adev@query-interface.com");
        user.setPassword(passwordEncoder.encode("Password1"));
        user.setOrigin("CF");
        user =  userRepository.save(user);

        Organization organization = new Organization("query-interface");
        organization.setUser(user);
        organization = organizationRepository.save(organization);

        Space space = new Space("dev");
        space.setOrganization(organization);
        spaceRepository.save(space);
    }
}
