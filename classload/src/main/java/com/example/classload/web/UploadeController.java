package com.example.classload.web;

import com.example.classload.compontent.LoadClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


/**
 * @Description: 上传下载管理API
 * @author: songxl
 * @time: 2021/7/20 12:39
 */
@RestController
@Api(value = "上传下载管理API", tags = {"上传下载管理API"})
@RequestMapping("/up")
public class UploadeController {
    @Autowired
    LoadClass loadClass;

    @ApiOperation(value = "反射调用测试 [by:songxl]")
    @PostMapping("/class")
    public Boolean getMenusTree() {
        Class<?> cls = loadClass.getClass("com\\example\\classload\\upclass",
                "com.example.classload.upclass.QuadOperation");
        try {
            //初始化一个实例
            Object obj = cls.newInstance();
            //方法名和相应的參数类型
            Method method = cls.getMethod("add", Double.class, Double.class);
            //调用得到的上边的方法method
            Object o = method.invoke(obj, 1.0, 2.0);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @ApiOperation(value = "测试 [by:songxl]")
    @GetMapping("/test")
    public String test() {
        return "hello";
    }

}
