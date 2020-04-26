package com.queryinterface.cflite.cloudcontroller.apps;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@RestController
@RequestMapping(path = "/api/v1")
public class ApplicationController {

    private static final String MINIO_URL = "http://localhost:9000";
    private static final String MINIO_ACCESS_KEY = "ACCESS1234";
    private static final String MINIO_SECRET_KEY = "SECRETPassword1";

    @RequestMapping(method = RequestMethod.POST, path = "/applications")
    public ResponseEntity<String> push(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
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
}
