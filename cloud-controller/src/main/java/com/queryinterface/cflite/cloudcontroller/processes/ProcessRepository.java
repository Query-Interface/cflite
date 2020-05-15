package com.queryinterface.cflite.cloudcontroller.processes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcessRepository extends CrudRepository<Process, UUID> {

    Iterable<Process> findAllByApplicationId(final UUID applicationId);

    Optional<Process> findFirstByApplicationIdAndType(final UUID applicationId, final String type);
}
