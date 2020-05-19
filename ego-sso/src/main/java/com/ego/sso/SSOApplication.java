package com.ego.sso;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SSO启动类
 *
 * @author zhoubin
 * @create 2019/12/17
 * @since 1.0.0
 */
@SpringBootApplication
//开启dubbo
@EnableDubboConfiguration
//扫描mapper接口包
@MapperScan("com.ego.sso.mapper")
public class SSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class,args);
    }
}
