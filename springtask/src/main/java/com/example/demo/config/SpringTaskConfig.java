package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:只需要在配置类中添加一个@EnableScheduling注解即可开启SpringTask的定时任务能力
 * @Author songxl
 * @Date 2021/10/25
 */
@Configuration
@EnableScheduling
public class SpringTaskConfig {
}
