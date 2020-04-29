package com.queryinterface.cflite.cloudcontroller.common;

import java.util.HashMap;
import java.util.Map;

public class ToOneRelationShip {
    private Map<String, String> data = new HashMap<>(1);

    public ToOneRelationShip(final String resourceId) {
        data.put("guid", resourceId);
    }

    public Map<String, String> getData() {
        return data;
    }
}
