package com.queryinterface.cflite.cloudcontroller.jobs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends CrudRepository<Job, UUID> {

}
