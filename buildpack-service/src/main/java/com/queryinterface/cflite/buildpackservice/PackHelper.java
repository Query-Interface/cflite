package com.queryinterface.cflite.buildpackservice;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PackHelper {

    // minio instance create with following docker command
    // docker run -p 9000:9000 --name minio1 \
    //  -e "MINIO_ACCESS_KEY=ACCESS1234" \
    //  -e "MINIO_SECRET_KEY=SECRETPassword1" \
    //  minio/minio server /data

    private static final String PACK = "/usr/bin/pack";
    private static final String MINIO_URL = "http://localhost:9000";
    private static final String MINIO_ACCESS_KEY = "ACCESS1234";
    private static final String MINIO_SECRET_KEY = "SECRETPassword1";

    public void build(final PushCommand command) throws MinioException, NoSuchAlgorithmException, IOException, InvalidKeyException  {
        final String applicationId = command.getBlobStoreId();
        final String applicationName = "test-app";
        final MinioClient minioClient = new MinioClient(MINIO_URL, MINIO_ACCESS_KEY, MINIO_SECRET_KEY);
        if (minioClient.bucketExists(applicationId)) {
            InputStream applicationCode = minioClient.getObject(applicationId, "code.zip");
            // store in temporary folder //${java.io.tmpdir}
            Path tempDirPath = Files.createTempDirectory(applicationId);

        }
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/usr/bin/pack", "build", applicationName, "--builder", "cnbs/sample-builder:bionic");
        // set working as the dir containing our source code
        // processBuilder.directory(new File("..."))
        // redirect output for streaming content ?
        // processBuilder.redirectOutput(new File("..."));
        try {
            Process process = processBuilder.start();
            process.getOutputStream();

            int exitCode = process.waitFor();
            // use completable future instead....
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
