package com.queryinterface.cflite.cloudcontroller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class UploadConfiguration {
    @Bean(name = "multipartResolver")
    public PutPostMultipartResolver multipartResolver() {
        PutPostMultipartResolver multipartResolver = new PutPostMultipartResolver();
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }
}
