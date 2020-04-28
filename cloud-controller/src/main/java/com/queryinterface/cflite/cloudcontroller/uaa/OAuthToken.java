package com.queryinterface.cflite.cloudcontroller.uaa;

public class OAuthToken {
    /*
    {
      "access_token" : "3a4d89bbf1e94f1682bae9f22e6b8345",
      "token_type" : "bearer",
      "id_token" : "eyJhbGciOiJIUzI1NiIsImprdSI6Imh0dHBzOi8vbG9jYWxob3N0OjgwODAvdWFhL3Rva2VuX2tleXMiLCJraWQiOiJsZWdhY3ktdG9rZW4ta2V5IiwidHlwIjoiSldUIn0.eyJzdWIiOiIzNmVlY2ZmNC1kNTM0LTQyNjQtOGYzNS1kMjk3MTllZWRkOTEiLCJhdWQiOlsiYXBwIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC91YWEvb2F1dGgvdG9rZW4iLCJleHAiOjE1ODY1MDA5MzUsImlhdCI6MTU4NjQ1NzczNSwiYW1yIjpbInB3ZCJdLCJhenAiOiJhcHAiLCJzY29wZSI6WyJvcGVuaWQiXSwiZW1haWwiOiJZTUx6SzBAdGVzdC5vcmciLCJ6aWQiOiJ1YWEiLCJvcmlnaW4iOiJ1YWEiLCJqdGkiOiIzYTRkODliYmYxZTk0ZjE2ODJiYWU5ZjIyZTZiODM0NSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJjbGllbnRfaWQiOiJhcHAiLCJjaWQiOiJhcHAiLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJZTUx6SzBAdGVzdC5vcmciLCJyZXZfc2lnIjoiYzYzZmYwNmEiLCJ1c2VyX2lkIjoiMzZlZWNmZjQtZDUzNC00MjY0LThmMzUtZDI5NzE5ZWVkZDkxIiwiYXV0aF90aW1lIjoxNTg2NDU3NzM1fQ.xDwbHa9lONGbNunZWpGrGdlMhPI6vHqiSWLYXjDwXmU",
      "refresh_token" : "6b4e322262d242c7a06c7f23f903aa0c-r",
      "expires_in" : 43199,
      "scope" : "scim.userids openid cloud_controller.read password.write cloud_controller.write",
      "jti" : "3a4d89bbf1e94f1682bae9f22e6b8345"
    }
     */

    private String access_token = "3a4d89bbf1e94f1682bae9f22e6b8345";
    private String token_type = "bearer";
    private String id_token = "eyJhbGciOiJIUzI1NiIsImprdSI6Imh0dHBzOi8vbG9jYWxob3N0OjgwODAvdWFhL3Rva2VuX2tleXMiLCJraWQiOiJsZWdhY3ktdG9rZW4ta2V5IiwidHlwIjoiSldUIn0.eyJzdWIiOiIzNmVlY2ZmNC1kNTM0LTQyNjQtOGYzNS1kMjk3MTllZWRkOTEiLCJhdWQiOlsiYXBwIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC91YWEvb2F1dGgvdG9rZW4iLCJleHAiOjE1ODY1MDA5MzUsImlhdCI6MTU4NjQ1NzczNSwiYW1yIjpbInB3ZCJdLCJhenAiOiJhcHAiLCJzY29wZSI6WyJvcGVuaWQiXSwiZW1haWwiOiJZTUx6SzBAdGVzdC5vcmciLCJ6aWQiOiJ1YWEiLCJvcmlnaW4iOiJ1YWEiLCJqdGkiOiIzYTRkODliYmYxZTk0ZjE2ODJiYWU5ZjIyZTZiODM0NSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJjbGllbnRfaWQiOiJhcHAiLCJjaWQiOiJhcHAiLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJZTUx6SzBAdGVzdC5vcmciLCJyZXZfc2lnIjoiYzYzZmYwNmEiLCJ1c2VyX2lkIjoiMzZlZWNmZjQtZDUzNC00MjY0LThmMzUtZDI5NzE5ZWVkZDkxIiwiYXV0aF90aW1lIjoxNTg2NDU3NzM1fQ.xDwbHa9lONGbNunZWpGrGdlMhPI6vHqiSWLYXjDwXmU";
    private String refresh_token = "6b4e322262d242c7a06c7f23f903aa0c";
    private int expires_in = 43199;
    private String scope = "scim.userids openid cloud_controller.read password.write cloud_controller.write";
    private String jti = "3a4d89bbf1e94f1682bae9f22e6b8345";

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getId_token() {
        return id_token;
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
}
