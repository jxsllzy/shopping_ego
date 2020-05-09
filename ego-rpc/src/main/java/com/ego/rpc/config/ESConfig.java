package com.ego.rpc.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Objects;

@Configuration
public class ESConfig {

    @Value("${elasticsearch.address}")
    private String[] address;

    // ES服务器连接方式
    private final static String SCHEME = "http";

    // 根据服务器地址构建 HttpHost 对象
    private HttpHost buildHttpHost(String s) {
        String[] address = s.split(":");
        if (2 != address.length) {
            return null;
        }
        String host = address[0];
        int port = Integer.parseInt(address[1]);
        return new HttpHost(host, port, SCHEME);
    }

    @Bean
    public RestClientBuilder restClientBuilder() {

        // 初始化 ES 服务器集群
        // 参数分别为：IP，端口，连接方式(默认为http)
//        private final static HttpHost[] httpHosts = {
//                new HttpHost(HOST, 9200, SCHEME),
//                new HttpHost(HOST, 9201, SCHEME),
//                new HttpHost(HOST, 9202, SCHEME)
//        };

        //上面的缩写
        HttpHost[] hosts = Arrays.stream(address)
                .map(this::buildHttpHost)
                .filter(Objects::nonNull)
                .toArray(HttpHost[]::new);
        return RestClient.builder(hosts);
    }

    /**
     * 创建客户端
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }

}