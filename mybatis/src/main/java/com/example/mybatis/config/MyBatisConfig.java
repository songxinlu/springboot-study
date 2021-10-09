package com.example.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:mybatis 配置类
 * @Author songxl
 * @Date 2021/10/9
 */
@Configuration
@MapperScan("com.example.mybatis.mapper")
public class MyBatisConfig {
}
