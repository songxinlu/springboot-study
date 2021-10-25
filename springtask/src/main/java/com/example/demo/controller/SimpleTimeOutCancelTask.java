package com.example.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:定时任务案例
 * @Author songxl
 * @Date 2021/10/25
 */
@Component
public class SimpleTimeOutCancelTask {
    //秒 分钟 小时 日 月 星期 年
    @Scheduled(cron = "0/10 * * * * ?")
    private void cancelTimeOutOrder() {
        System.out.println("每隔10秒执行一次");
    }
}
