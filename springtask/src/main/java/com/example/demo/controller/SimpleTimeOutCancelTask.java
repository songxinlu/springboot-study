package com.example.demo.controller;

import com.example.demo.task.LockTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description:定时任务案例
 * @Author songxl
 * @Date 2021/10/25
 */
@Component
public class SimpleTimeOutCancelTask {
    @Autowired
    LockTask lockTask;
    //秒 分钟 小时 日 月 星期 年
    @Scheduled(cron = "1 * * * * ?")
    private void cancelTimeOutOrder() {
//        System.out.println("每隔10秒执行一次");
        for(int i = 0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockTask.getMScheduledExecutorService().schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("每隔10秒执行一次");
                }
            },5, TimeUnit.SECONDS);
        }
    }
}
