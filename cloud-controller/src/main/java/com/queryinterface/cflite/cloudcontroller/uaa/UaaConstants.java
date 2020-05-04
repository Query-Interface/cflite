package com.queryinterface.cflite.cloudcontroller.uaa;

public class UaaConstants {
    public final static String TOKEN_TYPE = "JWT";
    public final static String TOKEN_HEADER = "Authorization";
    public final static String TOKEN_PREFIX = "bearer ";
    // TODO should be retrieved from an env variable (set from a secret)
    public final static String SIGN_KEY = "u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp";
}
