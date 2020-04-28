package com.queryinterface.cflite.cloudcontroller.common;

public class InfoLinkExperimental extends InfoLink {
    private boolean experimental = true;

    public InfoLinkExperimental(final String url) {
        super(url);
    }

    public boolean isExperimental() {
        return experimental;
    }
}
