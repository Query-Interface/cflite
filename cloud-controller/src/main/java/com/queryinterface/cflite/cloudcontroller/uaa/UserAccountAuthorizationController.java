package com.queryinterface.cflite.cloudcontroller.uaa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/uaa")
public class UserAccountAuthorizationController {

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public ResponseEntity<UaaInformation> login() {
        return ResponseEntity.ok(new UaaInformation());
    }

     /*
     {
        "app" : {
            "version" : "74.17.0"
        },
        "links" : {
            "uaa" : "http://localhost:8080/uaa",
            "passwd" : "/forgot_password",
            "login" : "http://localhost:8080/uaa",
            "register" : "/create_account"
        },
        "zone_name" : "uaa",
        "entityID" : "cloudfoundry-saml-login",
        "commit_id" : "git-metadata-not-found",
        "idpDefinitions" : { },
        "prompts" : {
            "username" : [ "text", "Email" ],
            "password" : [ "password", "Password" ]
        },
        "timestamp" : "2020-04-09T18:38:33+0000"
        }
    */
    private class UaaInformation {
        private Application app = new Application();
        private Map<String, String> links = new HashMap<>();
        private String zone_name = "uaa";
        private String entityID = "cloudfoundry-saml-login";
        private String commit_id= "git-metadata-not-found";
        private Map<String, String[]> prompts = new HashMap<>();
        private LocalDateTime timestamp = LocalDateTime.now();

        public UaaInformation() {
            links.put("uaa", "http://localhost:8080/api/uaa");
            links.put("passwd", "/forgot_password");
            links.put("login", "http://localhost:8080/api/uaa");
            links.put("register", "/create_account");

            prompts.put("username", new String[] {"text", "Email"});
            prompts.put("password", new String[] {"password", "Password"});
        }

         public Map<String, String> getLinks() {
             return links;
         }

         public Map<String, String[]> getPrompts() {
             return prompts;
         }
         public Application getApp() {
             return app;
         }
         public String getZone_name() {
             return zone_name;
         }
         public String getEntityID() {
             return entityID;
         }
         public String getCommit_id() {
             return commit_id;
         }
         public LocalDateTime getTimestamp() {
             return timestamp;
         }
    }

    private class Application {
        private String version = "74.17.0";
        public String getVersion() {
            return version;
        }
    }
}
