package org.teamwe.carrent.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.teamwe.carrent.controller.interceptor.PermitInterceptor;

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

    private final PermitInterceptor permitInterceptor;

    @Autowired
    public WebMvcConfig(PermitInterceptor permitInterceptor) {
        this.permitInterceptor = permitInterceptor;
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(allowedMethods)
                .allowedOrigins(allowedOrigins)
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permitInterceptor).addPathPatterns("/**");
    }

    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }
}
