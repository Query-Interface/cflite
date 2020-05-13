package com.queryinterface.cflite.cloudcontroller.apps;

import com.queryinterface.cflite.cloudcontroller.common.PaginatedResultV2;
import com.queryinterface.cflite.cloudcontroller.common.Resource;
import com.queryinterface.cflite.cloudcontroller.jobs.Job;
import com.queryinterface.cflite.cloudcontroller.jobs.JobDTO;
import com.queryinterface.cflite.cloudcontroller.jobs.JobRepository;
import com.queryinterface.cflite.cloudcontroller.spaces.Space;
import com.queryinterface.cflite.cloudcontroller.spaces.SpaceRepository;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path = "/api/v2")
public class ApplicationController {

    private static final String MINIO_URL = "http://localhost:9000";
    private static final String MINIO_ACCESS_KEY = "ACCESS1234";
    private static final String MINIO_SECRET_KEY = "SECRETPassword1";

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private SpaceRepository spaceRepository;
    @Autowired
    private JobRepository jobRepository;

    @RequestMapping(method = RequestMethod.PUT, path = "/applications")
    public ResponseEntity<String> pushTest(@RequestParam("file") MultipartFile file) {
        final String applicationId = UUID.randomUUID().toString();
        try {
            final MinioClient minioClient = new MinioClient(MINIO_URL, MINIO_ACCESS_KEY, MINIO_SECRET_KEY);
            minioClient.makeBucket(applicationId);
            PutObjectOptions options = new PutObjectOptions(file.getSize(), 0L);
            minioClient.putObject(applicationId, "code.zip", file.getInputStream(), options);
        } catch (MinioException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (InvalidKeyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(applicationId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/apps")
    public ResponseEntity<PaginatedResultV2> getApplication() {
        // [GET] /api/v2/apps?q=name:testingapp&q=space_guid:Afe225f21-561b-4f8e-8503-e67d5b8a9cf8
        // 2 filters send via q query parameter; see https://apidocs.cloudfoundry.org/13.0.0/apps/list_all_apps.html
        PaginatedResultV2 result = new PaginatedResultV2();
        return ResponseEntity.ok(result);
    }

    // [POST /api/v2/apps, headers=[host:"localhost:8080", user-agent:"cf.exe/6.51.0+2acd15650.2020-04-07 (go1.13.8; amd64 windows)", content-length:"91", accept:"application/json", authorization:"bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIyMjcxNjFlNS0yNGVmLTRiZTgtYTU2My1lY2YwZTE4NGRmZDYiLCJzdWIiOiJhZGV2QHF1ZXJ5LWludGVyZmFjZS5jb20iLCJleHAiOjE1ODg4ODQ0NjAsInVzZXJfbmFtZSI6ImFkZXZAcXVlcnktaW50ZXJmYWNlLmNvbSIsInVzZXJfaWQiOiIxMjM0Iiwib3JpZ2luIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2FwaSIsInNjb3BlIjpbInVhYS51c2VyIiwiY2xvdWRfY29udHJvbGxlci5yZWFkIiwicGFzc3dvcmQud3JpdGUiLCJjbG91ZF9jb250cm9sbGVyLndyaXRlIl19.9XzBgU4LNlK4yRjYXUPDOkXnY1wwpKD_-xsTSeQbiXnwjpAzNoTnQMN12fVwujKqi8i4Airav8E7emljOnpXWg", accept-encoding:"gzip", Content-Type:"application/json;charset=UTF-8"]]
    @RequestMapping(method = RequestMethod.POST, path = "/apps")
    public ResponseEntity addApplication(@RequestBody Application application) {
        Optional<Space> optionalSpace = spaceRepository.findById(application.getSpace_guid());
        if (optionalSpace.isEmpty()) {
            throw new RuntimeException("not found");
        }
        application.setSpace(optionalSpace.get());
        Resource resource = Resource.of(applicationRepository.save(application));
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/resource_match")
    public ResponseEntity addApplicationResources(@RequestBody List<ApplicationResource> resources)
    {
        // no need to store these resources description for now
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/apps/{guid}")
    public ResponseEntity<Application> getApplication(final @PathVariable String guid) {
        return ResponseEntity.ok(getApplicationByGuid(guid));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/apps/{guid}/bits", consumes = {"multipart/*"})
    public ResponseEntity<JobDTO> addZipFile(final @PathVariable String guid, final @RequestParam boolean async, @RequestPart(value = "application") MultipartFile file, @RequestPart(required = false) List<ApplicationResource> resources) {
        final Application app = getApplicationByGuid(guid);
        try {
            final MinioClient minioClient = new MinioClient(MINIO_URL, MINIO_ACCESS_KEY, MINIO_SECRET_KEY);
            minioClient.makeBucket(guid);
            PutObjectOptions options = new PutObjectOptions(file.getSize(), 0L);
            minioClient.putObject(guid, "code.zip", file.getInputStream(), options);
            app.setBlobStoreId("minio://"+guid+"/code.zip");

            var job = new Job("queued");
            job = jobRepository.save(job);
            return ResponseEntity.ok(new JobDTO(job));
        } catch (MinioException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InvalidKeyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Application getApplicationByGuid(final String guid) {
        final Optional<Application> optionalApplication = applicationRepository.findById(UUID.fromString(guid));
        if (optionalApplication.isEmpty()) {
            throw new ApplicationNotFoundException();
        }
        return optionalApplication.get();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/apps/{guid}/routes")
    public ResponseEntity getRoutes(final @PathVariable String guid) {
        // TODO
        return ResponseEntity.ok().build();
    }
}
