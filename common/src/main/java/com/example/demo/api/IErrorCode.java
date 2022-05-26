package com.example.demo.api;

/**
 * @Description: 封装API的错误码
 * @author: rengb
 * @time: 2021/1/5 18:27
 */
public interface IErrorCode {

    long getCode();

    String getMessage();

}