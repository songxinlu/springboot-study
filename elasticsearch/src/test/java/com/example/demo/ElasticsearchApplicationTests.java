package com.example.demo;

import javafx.application.Application;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElasticsearchApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("...");
    }

//    @Autowired
//    private RestHighLevelClient client;
//    @Test
//    public void testES(){
//        System.out.println(client);
//    }
}
