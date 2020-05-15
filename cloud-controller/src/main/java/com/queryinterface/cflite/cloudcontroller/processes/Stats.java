package com.queryinterface.cflite.cloudcontroller.processes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    private String type = "web";
    private int index = 0;
    private String state = "DOWN";
    private int uptime = 0;
    private String isolationSegment = null;
    private String details = null;

    public Stats(final String type, final int index) {
        this.type = type;
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public String getState() {
        return state;
    }

    public int getUptime() {
        return uptime;
    }

    @JsonProperty("isolationSegment")
    public String getIsolationSegment() {
        return isolationSegment;
    }

    public String getDetails() {
        return details;
    }
}
