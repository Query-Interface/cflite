package com.queryinterface.cflite.buildpackservice;

public class PushCommand {
    private String applicationName;
    private String blobStoreId;

    public String getBlobStoreId() {
        return blobStoreId;
    }

    public void setBlobStoreId(String blobStoreId) {
        this.blobStoreId = blobStoreId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
