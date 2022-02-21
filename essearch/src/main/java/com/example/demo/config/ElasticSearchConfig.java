package com.example.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author songxl
 * @Date 2021/11/17
 */
@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient esRestClinet() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("101.43.37.173", 9200, "http")));
        return client;
    }
}
