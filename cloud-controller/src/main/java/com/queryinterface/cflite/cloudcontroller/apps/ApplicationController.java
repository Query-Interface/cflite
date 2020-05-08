package com.queryinterface.cflite.cloudcontroller.apps;

import com.queryinterface.cflite.cloudcontroller.common.PaginatedResultV2;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@RestController
@RequestMapping(path = "/api/v2")
public class ApplicationController {

    private static final String MINIO_URL = "http://localhost:9000";
    private static final String MINIO_ACCESS_KEY = "ACCESS1234";
    private static final String MINIO_SECRET_KEY = "SECRETPassword1";

    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/applications")
    public ResponseEntity<String> pushTest(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
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
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationRepository.save(application));
    }
}
