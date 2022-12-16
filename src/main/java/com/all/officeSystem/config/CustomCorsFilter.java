package com.all.officeSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 自定义跨域过滤器
 */
@Configuration
public class CustomCorsFilter {

    @Bean
    public FilterRegistrationBean corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false); // 表示是否允许请求带有验证信息
        config.addAllowedOrigin("*"); // 表示允许所有，可以设置需要的地址
        config.addAllowedHeader("*"); // 表示允许带请求头的
        config.addAllowedMethod("*"); // 允许所有的请求方法
        source.registerCorsConfiguration("/**", config); // CORS配置对所有接口都有效
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}