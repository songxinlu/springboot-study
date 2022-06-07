package com.example.demo.enums;

import lombok.Getter;

/**
 * @Description: 消息队列枚举配置
 * @author: songxl
 * @time: 2022/6/1 10:40
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列-基础信息变更导致redis需要刷新
     */
    QUEUE_REDIS_CACHE_REFRESH("emrais.redis.cache.refresh.direct", "emrais.redis.cache.refresh.queue", "emrais.redis.cache.refresh.routekey"),

    /**
     * 消息通知队列-用户登录成功后记录日志
     */
    QUEUE_LOGIN_SUCESS_RECORD("emrais.login.success.record.direct", "emrais.login.success.record.queue", "emrais.login.success.record.routekey"),

    /**
     * 消息通知队列-用户操作后记录日志
     */
    QUEUE_OPER_LOG_RECORD("emrais.oper.log.record.direct", "emrais.oper.log.record.queue", "emrais.oper.log.record.routekey"),

    /**
     * 消息通知队列-用户操作异常记录日志
     */
    QUEUE_OPER_ERROR_RECORD("emrais.oper.error.record.direct", "emrais.oper.error.record.queue", "emrais.oper.error.record.routekey");

    /**
     * 交换名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
