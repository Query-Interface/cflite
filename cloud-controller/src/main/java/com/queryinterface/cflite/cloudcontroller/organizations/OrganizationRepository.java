package com.queryinterface.cflite.cloudcontroller.organizations;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, UUID> {
    Iterable<Organization> findAllByUserId(final UUID userId);
}
