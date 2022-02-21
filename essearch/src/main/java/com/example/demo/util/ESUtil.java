package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.core.ESTemplate;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author songxl
 * @Date 2021/12/3
 */
@Component
public class ESUtil<T> {
    @Autowired
    ESTemplate esTemplate;

    /**
     * @param t     保存对象
     * @param index 索引
     * @param type  类型
     * @Description保存
     * @Return boolean
     */
    public boolean save(T t, String index, String type) {
        IndexRequest indexRequest = new IndexRequest(index, type);
        String jsonString = JSONObject.toJSONString(t);
//        必须指定XContentType
        indexRequest.source(jsonString, XContentType.JSON);
        // 执行
        try {
            // 获取响应数据
            IndexResponse response = esTemplate.restHighLevelClient().index(indexRequest, RequestOptions.DEFAULT);
            if (1 == response.getShardInfo().getSuccessful()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean updateById(T t, String id, String index, String type) {
        UpdateRequest updateRequest = new UpdateRequest(index, type, id).doc(JSONObject.toJSONString(t), XContentType.JSON);
        try {
            UpdateResponse updateResponse = esTemplate.restHighLevelClient().update(updateRequest, RequestOptions.DEFAULT);
            if (1 == updateResponse.getShardInfo().getSuccessful()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    public void updateByQuery(String index, String type, QueryBuilder query, Script script) {
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(index);
        updateByQueryRequest.setDocTypes(type);
        updateByQueryRequest.setQuery(query);
        updateByQueryRequest.setScript(script);
        try {
            esTemplate.restHighLevelClient().updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Map<String, Object>> termsQuery(SearchSourceBuilder sourceBuilder, String index, String type) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = esTemplate.restHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
            //解析返回
            if (response.status() != RestStatus.OK || response.getHits().getTotalHits() <= 0) {
                return Collections.emptyList();
            }
            return Arrays.stream(response.getHits().getHits()).map(b -> {
                return b.getSourceAsMap();
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void saveAnsyc(T t, String index, String type) {
        IndexRequest indexRequest = new IndexRequest(index, type);
        String jsonString = JSONObject.toJSONString(t);
//        必须指定XContentType
        indexRequest.source(jsonString, XContentType.JSON);
        //异步执行
        //IndexResponse 的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                //执行成功时调用。 Response以参数方式提供
                System.out.println(indexRequest);
            }

            @Override
            public void onFailure(Exception e) {
                //在失败的情况下调用。 引发的异常以参数方式提供
                System.out.println(e.getMessage());
            }
        };
        //异步执行索引请求需要将IndexRequest实例和ActionListener实例传递给异步方法：
        esTemplate.restHighLevelClient().indexAsync(indexRequest, RequestOptions.DEFAULT, listener);
    }

    public boolean deleteByIds(List<String> ids, String index, String type) {
        if (ids != null && ids.size() > 0) {
            AtomicBoolean out = new AtomicBoolean(false);
            ids.stream().forEach(id -> {
                DeleteRequest deleteRequest  = new DeleteRequest(index,type,id);
                try {
                    DeleteResponse deleteResponse = esTemplate.restHighLevelClient().delete(deleteRequest,RequestOptions.DEFAULT);
                    if (1 == deleteResponse.getShardInfo().getSuccessful()) {
                        out.set(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.set(false);
                    return;
                }
            });
            return out.get();
        }

        return false;
    }
}
