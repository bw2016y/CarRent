package org.teamwe.carrent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * @author FDws
 * Created on 2018/6/13 9:59
 */
@Configuration
public class InitBeans {
    @Bean("multipartResolver")
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
}
