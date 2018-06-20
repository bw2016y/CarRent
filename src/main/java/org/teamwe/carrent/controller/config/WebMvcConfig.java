package org.teamwe.carrent.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author FDws
 * Created on 2018/6/19 15:01
 */
@Configuration
@EnableRedisHttpSession
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * Allowed Origins
     */
    @Value("#{'${project.controller.allowed.origins}'.split(', *')}")
    private String[] allowedOrigins;

    @Value("#{'${project.controller.allowed.methods:GET, POST, PUT, DELETE, OPTIONS}'.split(', *')}")
    private String[] allowedMethods;

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(allowedMethods)
                .allowedOrigins(allowedOrigins);
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }
}
