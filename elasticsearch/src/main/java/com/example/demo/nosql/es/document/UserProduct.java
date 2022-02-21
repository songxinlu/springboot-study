package com.example.demo.nosql.es.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @Description:用户文档
 * @Author songxl
 * @Date 2021/10/26
 */
@Data
@Document(indexName = "elasticsearch")
public class UserProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    @Field(type = FieldType.Text)
    private String username;
    @Field(type = FieldType.Text)
    private String password;
}
