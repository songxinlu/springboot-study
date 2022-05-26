package com.example.demo.exception;


import com.example.demo.api.IErrorCode;

/**
 * @Description: 断言处理类，用于抛出各种API异常
 * @author: rengb
 * @time: 2021/1/5 18:27
 */
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

}