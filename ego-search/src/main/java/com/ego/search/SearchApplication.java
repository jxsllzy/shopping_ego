package com.ego.search;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ego.search.mapper")
@EnableDubboConfiguration
public class SearchApplication {
    public static void main(String[] args){
        SpringApplication.run(SearchApplication.class,args);
    }
}
