package com.queryinterface.cflite.cloudcontroller.apps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, UUID> {
    Optional<Application> findTopByName(final String name);

    Iterable<Application> findAllBySpaceId(final UUID spaceId);

    Iterable<Application> findByNameInAndSpaceIdIn(final List<String> applicationNames, final List<UUID> spaceGuids);
}
