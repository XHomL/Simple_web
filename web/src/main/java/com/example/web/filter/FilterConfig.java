package com.example.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 过滤器配置
 */
@Configuration
public class FilterConfig  {
    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(AccessFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("AccessFilter");
        return registration;
    }
    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "sessionFilter")
    public Filter AccessFilter() {
        return new AccessFilter();
    }
}
