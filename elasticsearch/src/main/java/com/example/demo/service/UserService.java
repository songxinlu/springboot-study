package com.example.demo.service;

import com.example.demo.nosql.es.document.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Page<UserProduct> list();
}
