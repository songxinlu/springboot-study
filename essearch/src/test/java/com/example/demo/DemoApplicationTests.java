package com.example.demo;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import com.example.demo.entiity.User;
import com.example.demo.util.ESUtil;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    @Autowired
    ESUtil esUtil;

    @Test
    public void contextLoads() {
        /**
         *  DSL
        User user = new User();
        user.setAge(93);
        user.setUsername("屠呦呦");
        user.setGender("女");
        user.setIntroduce("青蒿素发明者");
        System.out.println(esUtil.save(user,"user1","user"));
//        System.out.println(esUtil.updateById(user, "0jQsf30BJzUbCm7sc5Wx", "user", "es6type"));
//        System.out.println(esUtil.deleteByIds(Lists.newArrayList("0jQsf30BJzUbCm7sc5Wx"),"user","es6type"));



        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //eq
        TermsQueryBuilder termsQueryBuilder = QueryBuilders
                .termsQuery("username", Lists.newArrayList("张三", "李四", "王五"));
        // and,or,not
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(termsQueryBuilder);

        sourceBuilder.query(QueryBuilders.boolQuery()
                .should(QueryBuilders.termsQuery("username",Lists.newArrayList("张三", "李四")))
                .must(QueryBuilders.rangeQuery("age").gte(18).lt(19))
                .mustNot(QueryBuilders.termQuery("gender","男")));
        //not null(只能一个字段)
        ExistsQueryBuilder existsQueryBuilder = new ExistsQueryBuilder("username");
        ExistsQueryBuilder existsQueryBuilder1 = new ExistsQueryBuilder("age");

        //以什么开头
        PrefixQueryBuilder prefixQueryBuilder = new PrefixQueryBuilder("username","张");

        //通配符
        //*：匹配多个字符
        //?：匹配单个字符
        WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder("username","*1");
//        sourceBuilder.query(wildcardQueryBuilder);

        List<Map<String,User>> a = esUtil.termsQuery(sourceBuilder,"user","es6type");
        System.out.println(a);

         */

        /*
            SQL
         */
        String selectSql = "select * from user where u._type = 'es6type' and  u.username = '澜澜' and u._id = '0TQpf30BJzUbCm7sX5WT'";
        String updateSql = "UPDATE user SET username='澜澜2' WHERE (_id='0TQpf30BJzUbCm7sX5WT')";
        String out =HttpUtil.createPost("http://101.43.37.173:9200/_sql")
                .contentType(ContentType.JSON.toString())
        .body(selectSql).execute().body();
        System.out.println(out);
    }
}
