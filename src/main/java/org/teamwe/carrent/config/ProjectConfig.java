package org.teamwe.carrent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author FDws
 * Created on 2018/6/19 15:44
 */
@Configuration
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {
    /**
     * Allowed Origins
     */
    private String[] allowedOrigins = {};

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
