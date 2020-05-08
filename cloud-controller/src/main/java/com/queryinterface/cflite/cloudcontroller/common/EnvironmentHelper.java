package com.queryinterface.cflite.cloudcontroller.common;

public class EnvironmentHelper {

    public static String getSystemProperty(final String propertyName, final String defaultValue) {
        final String value = System.getenv(propertyName);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
}
