package org.teamwe.carrent.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author FDws
 * Created on 2018/6/19 15:01
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * Allowed Origins
     */
    @Value("#{'${project.allowed.origins}'.split(', *')}")
    private String[] allowedOrigins;

    @Value("#{'${project.allowed.methods:GET, POST, PUT, DELETE}'.split(', *')}")
    private String[] allowedMethods;

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }
}
