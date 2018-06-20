package org.teamwe.carrent.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author FDws
 * Created on 2018/6/20 16:51
 */
@Configuration
public class PermitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }

        if (allowedAll(request.getRequestURI(), request.getMethod())) {
            return true;
        }

        return false;
    }

    private boolean allowedAll(String url, String method) {
        method = method.toUpperCase();
        String[][] patterns = {
                {"/", "GET"},
                {"/validate", "GET"},
                {"/session/status", "GET"},
                {"/password/[^/]+", "GET"},
                {"/password", "PUT"},
                {"/type", "GET"},
                {"/brand", "GET"},
                {"/images/.+", "GET"},
                {"/car", "GET"},
                {"/car/pages", "GET"},
                {"/city", "GET"},
                {"/comment/.+", "GET"}
        };

        for (String[] pat : patterns) {
            if (url.matches(pat[0]) && pat[1].equals(method)) {
                return true;
            }
        }
        return false;
    }
}
