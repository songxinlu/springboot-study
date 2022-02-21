package com.example.demo.task;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description:
 * @Author songxl
 * @Date 2021/11/24
 */
@Component
@Data
public class LockTask {
    //定时执行线程池
    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(10);
}
