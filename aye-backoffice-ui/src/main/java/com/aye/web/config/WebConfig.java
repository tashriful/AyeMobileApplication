package com.aye.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.List;

/**
 * Created by Toufiq on 11/6/2019.
 */
@Configuration
public class WebConfig
        implements WebMvcConfigurer
{


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    @Bean
    public ViewResolver beanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        return resolver;
    }


//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setFallbackPageable(PageRequest.of(0, 8, Sort.Direction.ASC, "id"));
//        argumentResolvers.add(resolver);
//    }


    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }


//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }


}
