package com.elvin.aaos.web.session;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestCacheUtility {

    private static RequestCache requestCache = null;

    public static void save(HttpServletRequest request, HttpServletResponse response) {

        if (requestCache == null)
            requestCache = new HttpSessionRequestCache();

        requestCache.saveRequest(request, response);
    }

    public static RequestCache get() {

        if (requestCache == null)
            return new HttpSessionRequestCache();

        return requestCache;
    }

    public static void removeRequest(HttpServletRequest request, HttpServletResponse response) {
        requestCache.removeRequest(request, response);
    }

}
