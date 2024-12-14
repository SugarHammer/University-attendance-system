package com.lxq.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 * 添加拦截器 用户信息校验
 */
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("WebMvcConfigurer:addInterceptors被执行！！");
        registry.addInterceptor(new UrlInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/");
    }
    //添加静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源放行
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
