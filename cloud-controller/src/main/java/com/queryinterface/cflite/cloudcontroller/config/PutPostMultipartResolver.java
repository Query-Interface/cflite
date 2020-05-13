package com.queryinterface.cflite.cloudcontroller.config;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * By default CommonsMultipartResolver only allows MultiPart on POST calls.
 * This class add support for PUT
 */
public final class PutPostMultipartResolver extends CommonsMultipartResolver {
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        final String method = request.getMethod().toLowerCase(Locale.ENGLISH);
        return !"post".equalsIgnoreCase(method) && !"put".equalsIgnoreCase(method) ? false : FileUploadBase.isMultipartContent(new ServletRequestContext(request));
    }
}
