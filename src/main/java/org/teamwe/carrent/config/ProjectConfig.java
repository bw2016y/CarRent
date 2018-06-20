package org.teamwe.carrent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author FDws
 * Created on 2018/6/19 15:44
 */
@Configuration
public class ProjectConfig {
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
