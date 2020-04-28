package com.queryinterface.cflite.cloudcontroller.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InfoController {

    @RequestMapping(method = RequestMethod.GET, path = "/v2/info")
    public ResponseEntity<Info> getInfo() {
        return ResponseEntity.ok(new Info());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        String ss = "{\"links\": {\n" +
                "    \"self\": {\n" +
                "      \"href\": \"http://localhost:8080/api\"\n" +
                "    },\n" +
                "    \"bits_service\": {\n" +
                "      \"href\": \"http://localhost:8080/api/bits\"\n" +
                "    },\n" +
                "    \"cloud_controller_v2\": {\n" +
                "      \"href\": \"http://localhost:8080/api/v2\",\n" +
                "      \"meta\": {\n" +
                "        \"version\": \"2.146.0\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"cloud_controller_v3\": {\n" +
                "      \"href\": \"http://localhost:8080/api/v3\",\n" +
                "      \"meta\": {\n" +
                "        \"version\": \"3.81.0\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"network_policy_v0\": {\n" +
                "      \"href\": \"http://localhost:8080/api/networking/v0/external\"\n" +
                "    },\n" +
                "    \"network_policy_v1\": {\n" +
                "      \"href\": \"http://localhost:8080/api/networking/v1/external\"\n" +
                "    },\n" +
                "    \"login\": {\n" +
                "      \"href\": \"http://localhost:8080/api/login\"\n" +
                "    },\n" +
                "    \"uaa\": {\n" +
                "      \"href\": \"http://localhost:8080/api/uaa\"\n" +
                "    },\n" +
                "    \"credhub\": null,\n" +
                "    \"routing\": null,\n" +
                "    \"logging\": {\n" +
                "      \"href\": \"wss://doppler.query-interface.com:443\"\n" +
                "    },\n" +
                "    \"log_cache\": {\n" +
                "      \"href\": \"http://log-cache.query-interface.com\"\n" +
                "    },\n" +
                "    \"log_stream\": {\n" +
                "      \"href\": \"http://log-stream.query-interface.com\"\n" +
                "    } " +
                "  }}";
        return ResponseEntity.ok(ss);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v3")
    public ResponseEntity getV3Info() {
        return ResponseEntity.ok(new InfoV3());
    }

    private class Info {
        private String name = "vcap";
        private String build = "2222";
        private String support = "";
        private String version = "1";
        private String description = "CF Lite";
        private String authorization_endpoint = "http://localhost:8080/api/uaa";
        private String token_endpoint = "http://localhost:8080/uaa";
        private String min_cli_version = null;
        private String min_recommended_cli_version = null;
        private String api_version = "2.146.0";
        private String app_ssh_endpoint = "ssh.system.domain.example.com:2222";
        private String app_ssh_host_key_fingerprint ="47:0d:d1:c8:c3:3d:0a:36:d1:49:2f:f2:90:27:31:d0";
        private String app_ssh_oauth_client = null;
        private String routing_endpoint = "http://localhost:30000";
        private String logging_endpoint = "ws://loggregator.vcap.me:80";

        public String getName() {
            return name;
        }

        public String getBuild() {
            return build;
        }

        public String getSupport() {
            return support;
        }

        public String getVersion() {
            return version;
        }

        public String getDescription() {
            return description;
        }

        public String getAuthorization_Endpoint() {
            return authorization_endpoint;
        }

        public String getTokenEndpoint() {
            return token_endpoint;
        }

        public String getMin_cli_version() {
            return min_cli_version;
        }

        public String getMin_recommended_cli_version() {
            return min_recommended_cli_version;
        }

        public String getApi_version() {
            return api_version;
        }

        public String getApp_ssh_endpoint() {
            return app_ssh_endpoint;
        }

        public String getApp_ssh_host_key_fingerprint() {
            return app_ssh_host_key_fingerprint;
        }

        public String getApp_ssh_oauth_client() {
            return app_ssh_oauth_client;
        }

        public String getRouting_endpoint() {
            return routing_endpoint;
        }

        public String getLogging_endpoint() {
            return logging_endpoint;
        }
    }
}


