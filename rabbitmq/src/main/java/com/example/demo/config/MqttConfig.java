package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:发布/订阅通讯协议MQTT相关配置
 * @author: songxl
 * @time: 2022/6/1 10:15
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbitmq.mqtt")
public class MqttConfig {
    /**
     * RabbitMQ的MQTT默认topic
     */
    private String defaultTopic;

    /**
     * RabbitMQ的MQTT连接地址
     */
    private String url;

    /**
     * RabbitMQ的MQTT前端页面连接地址
     */
    private String frontUrl;
}
