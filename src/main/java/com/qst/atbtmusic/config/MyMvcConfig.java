package com.qst.atbtmusic.config;

import com.qst.atbtmusic.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
            @Override
          public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/index.html").setViewName("main");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry){
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/main","/user/login","/user/register","/j");
            }
        };

        return adapter;
    }

}
