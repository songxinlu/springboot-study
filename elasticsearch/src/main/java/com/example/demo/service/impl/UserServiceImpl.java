package com.example.demo.service.impl;

import com.example.demo.nosql.es.document.UserProduct;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author songxl
 * @Date 2021/10/26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserServiceImpl userService;

    @Override
    public Page<UserProduct> list() {
        return userService.list();
    }
}
