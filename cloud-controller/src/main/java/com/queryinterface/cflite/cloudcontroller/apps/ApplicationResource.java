package com.queryinterface.cflite.cloudcontroller.apps;

import com.fasterxml.jackson.annotation.JsonProperty;

// {"fn":".gitattributes","mode":"766","sha1":"2625b53183ad219ddb34d968ae74e24a6a7addab","size":12}
public class ApplicationResource {
    @JsonProperty("fn")
    private String fileName;
    private String mode = "749";
    @JsonProperty("sha1")
    private String signature;
    private int size;

    public ApplicationResource() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
