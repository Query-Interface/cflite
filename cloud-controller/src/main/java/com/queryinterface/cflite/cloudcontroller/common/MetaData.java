package com.queryinterface.cflite.cloudcontroller.common;

import java.util.HashMap;
import java.util.Map;

public class MetaData {
    private Map<String, String> labels = new HashMap<>();
    private Map<String, String> annotations = new HashMap<>();

    public MetaData() {

    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Map<String, String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Map<String, String> annotations) {
        this.annotations = annotations;
    }
}
