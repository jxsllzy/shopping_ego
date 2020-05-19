package com.ego.rpc;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//dubbo开启
@EnableDubboConfiguration
//扫描mybatis配置
@MapperScan("com.ego.rpc.mapper")
public class RpcApplication {
    public static void main(String[] args) {

        SpringApplication.run(RpcApplication.class,args);
    }
}
