package com.ego.search;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
//扫描mapper接口
@MapperScan("com.ego.search.mapper")
//dubbo配置
@EnableDubboConfig
public class SearchApplication {
    public static void main(String[] args) {
            SpringApplication.run(SearchApplication.class,args);
    }
}
