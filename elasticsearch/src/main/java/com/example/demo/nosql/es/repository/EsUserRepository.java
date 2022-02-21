package com.example.demo.nosql.es.repository;

import com.example.demo.nosql.es.document.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsUserRepository extends ElasticsearchRepository<UserProduct, Long> {
    Page<UserProduct> list();
}
