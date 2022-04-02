package com.cy.travels.config;

import com.cy.travels.interceptor.LoginInterceptor;
import com.cy.travels.interceptor.NoLoginInterceptor;
import com.fasterxml.jackson.core.format.MatchStrength;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 将拦截器添加到容器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())//给swagger添加上和header
                .addPathPatterns("/swagger-ui.html");
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/","/front/user/login","/front/user/register")//放行登录注册相关请求
                .excludePathPatterns("/static/**")//放行静态资源相关请求
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");//放行swagger相关请求请求
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/upload/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("file:D:/upload/")
                .addResourceLocations("file:/upload/");//配合放在云服务器根目录下的上传文件
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
