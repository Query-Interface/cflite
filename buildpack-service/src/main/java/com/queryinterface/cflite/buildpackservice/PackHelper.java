package com.queryinterface.cflite.buildpackservice;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
        final MinioClient minioClient = new MinioClient(
                getSystemProperty("MINIO_URL", MINIO_URL),
                getSystemProperty("MINIO_ACCESS_KEY", MINIO_ACCESS_KEY),
                getSystemProperty("MINIO_SECRET_KEY", MINIO_SECRET_KEY));
        if (minioClient.bucketExists(applicationId)) {
            try (InputStream applicationCode = minioClient.getObject(applicationId, "code.zip")) {
                // store in temporary folder
                final Path tempDirPath = Files.createTempDirectory(applicationId);
                final Path filePath = tempDirPath.resolve("code.zip");
                Files.copy(applicationCode, filePath);
                unzip(filePath, tempDirPath);
                Files.delete(filePath);

                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.inheritIO();
                processBuilder.command(PACK, "build", command.getApplicationName(), "--builder", "cnbs/sample-builder:bionic");
                // set working as the dir containing our source code
                 processBuilder.directory(tempDirPath.toFile());
                // redirect output
                //processBuilder.redirectOutput(tempDirPath.resolve("out.log").toFile());
                try {
                    Process process = processBuilder.start();
                    process.getOutputStream();

                    int exitCode = process.waitFor();
                    // use completable future instead....
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private String getSystemProperty(final String propertyName, final String defaultValue) {
        final String value = System.getenv(propertyName);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    private void unzip(final Path zipFile, final Path outputDir) {
        byte[] buffer = new byte[1024];
        try (FileInputStream fileInput = new FileInputStream(zipFile.toFile());
             ZipInputStream zipInput = new ZipInputStream(fileInput)) {
            ZipEntry zEntry;
            while ((zEntry = zipInput.getNextEntry()) != null) {
                Path unzipToFile = outputDir.resolve(zEntry.getName()).normalize();
                if (zEntry.isDirectory()) {
                    Files.createDirectories(unzipToFile);
                } else {
                    try (FileOutputStream fileOutput = new FileOutputStream(unzipToFile.toFile())) {
                        int size;
                        while ((size = zipInput.read(buffer)) > 0) {
                            fileOutput.write(buffer, 0, size);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
