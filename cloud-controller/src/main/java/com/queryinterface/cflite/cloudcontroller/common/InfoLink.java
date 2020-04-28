package com.queryinterface.cflite.cloudcontroller.common;

public class InfoLink {
    private String href;

    public InfoLink(final String url) {
        this.setHref(url);
    }

    public String getHref() {
        return href;
    }

    public void setHref(final String href) {
        this.href = href;
    }
}
