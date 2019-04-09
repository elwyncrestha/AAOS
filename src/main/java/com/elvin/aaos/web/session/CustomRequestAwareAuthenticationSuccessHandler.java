package com.elvin.aaos.web.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = null;

    private HttpServletRequest request = null;

    private final String defaultURL = (request != null ? request.getContextPath() : "") + "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        requestCache = RequestCacheUtility.get();

        this.request = request;

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {

            clearAuthenticationAttributes(request);

            getRedirectStrategy().sendRedirect(request, response, defaultURL);

            return;
        }

        String targetUrlParameter = getTargetUrlParameter();

        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                .getParameter(targetUrlParameter)))) {

            RequestCacheUtility.removeRequest(request, response);

            clearAuthenticationAttributes(request);

            getRedirectStrategy().sendRedirect(request, response, defaultURL);

            return;
        }

        String targetUrl = savedRequest.getRedirectUrl();

        RequestCacheUtility.removeRequest(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

}
