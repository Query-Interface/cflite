package com.queryinterface.cflite.cloudcontroller.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Resources<T> {
    private final List<T> resources = new ArrayList<>();

    public Resources(T... resources) {
        Collections.addAll(this.resources, resources);
    }

    public List<T> getResources() {
        return resources;
    }
}
