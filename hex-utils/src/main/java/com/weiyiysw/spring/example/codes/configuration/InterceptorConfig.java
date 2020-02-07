package com.weiyiysw.spring.example.codes.configuration;

import com.weiyiysw.spring.example.codes.interceptors.ReqResLoggerInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author yishiwei
 * @date 2019/9/5
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private ReqResLoggerInterceptors reqResLoggerInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqResLoggerInterceptors).addPathPatterns("/**");
    }
}
