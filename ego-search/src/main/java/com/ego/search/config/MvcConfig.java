package com.ego.search.config;

import com.ego.search.interceptor.SearchCommonInterceptor;
import com.ego.search.interceptor.SearchNoLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private SearchCommonInterceptor searchCommonInterceptor;

    @Autowired
    private SearchNoLoginInterceptor searchNoLoginInterceptor;
    /**
     * 放行静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }

    /**
     * 自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(searchCommonInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(searchNoLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/user/login/**")
                .excludePathPatterns("/findShopByName")
                .excludePathPatterns("/search/searchShopByName");
    }
}
