package com.queryinterface.cflite.cloudcontroller.uaa;

public class AuthToken {

    private String access_token;
    private String token_type = "bearer";
    private String refresh_token;
    private int expires_in = 86400;
    private String scope = "cloud_controller.read";
    private String jti;

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

    public String getJti() {
        return jti;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
