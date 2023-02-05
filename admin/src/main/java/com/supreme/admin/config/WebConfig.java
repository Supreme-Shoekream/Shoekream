package com.supreme.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/Users/oyun-yeong/img/")
//                .addResourceLocations("file:///Users/oyun-yeong/img/") -> window version
                .setCachePeriod(3600) // 24시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}