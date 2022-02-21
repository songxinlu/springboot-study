package com.example.demo.util;


/**
 * 封装了对ElasticSearch的增删改查操作
 * Created by zhufanfan on 2017/5/15 0015.
 */
public class ElasticSearchIO {

//    public static String ES_URL= "http://120.26.51.135:9200";
//    public static String ES_URL= "http://192.168.100.2:9200";
    public static String ES_URL= "http://60.12.233.37:9200";

    //一次查询返回的结果个数
    private int MAX_SIZE_FILTER_BY_INDEX=10000;
    private int MAX_SIZE_FILTER_BY_INDEX_AND_TYPE=10000;
    private int MAX_SIZE_FILTER_BY_INDEX_AND_TYPE_AND_KEY=10000;

    /**
     * 创建一个索引，相当于：数据库
     * 此方法有bug，不可用。可以在操作界面新建索引
     * ElasticSearch的索引依次为index/type/document
     * @param index
     */
    public void createIndex(String index){
        String url=ES_URL+"/"+index;
        String res= HttpRequestUtils.doPost(url,"");
        System.out.println(res);
    }


    /**
     * 创建一个Type，相当于：表
     * 此方法有bug，已经被废弃。
     * @param index
     * @param type
     */
    public void createType(String index, String type){
        String url=ES_URL+"/"+index+"/"+type+"/";
        String res= HttpRequestUtils.doPost(url,"");
    }


    /**
     * 功能1：新建一个type，并插入一条document
     * 功能2：插入一条document
     * @param index
     * @param type
     * @param document
     */
    public String insert(String index, String type, String document){
        String url=ES_URL+"/"+index+"/"+type+"/";
        String message=document;
        String res= HttpRequestUtils.doPost(url, message);
//        String res= HttpRequestUtils.doPost(url, message);
//        System.out.println(res);
        return res;
    }

    /**
     * 根据index、type、id来删除一个document
     * @param index
     * @param type
     * @param id
     */
    public void delete(String index, String type,String id){
        String url=ES_URL+"/"+index+"/"+type+"/"+id;
//        HttpMethod.httpDelete(url);
    }
    
    public void delete(String index, String type){
    	String url=ES_URL+"/"+index+"/"+type;
//    	HttpMethod.httpDelete(url);
    }

    /**
     * 根据字段删除
     * @param index
     * @param type
     * @param key
     * @param value
     */
    public void delete(String index, String type,String key,String value){
        String url=ES_URL+"/"+index+"/"+type+"/"+"_delete_by_query";
        String message="{\"query\":{\"match\":{\"key\":\"value\"}}}";
        message=message.replace("key",key).replace("value",value);
        HttpRequestUtils.doPost(url,message);
    }

//    public void delete(String index, String type,String[] keys,String[] values){
//        String url=ES_URL+"/"+index+"/"+type+"/"+"_delete_by_query";
//        String message="{\"query\":{\"match\":{";
//        message=message.replace("key",keys[0]).replace("value",values[0]);
//        for(int i=0;i<keys.length;i++){
//            message=message+"\"key\":\"value\",";
//            System.out.println(message);
//            message=message.replace("key",keys[i]).replace("value",values[i]);
//        }
//        message=message.substring(0,message.length()-1)+"}}}";
//        System.out.println(message);
//        HttpMethod.httpPost(url,message);
//    }

    /**
     * 读取index下的所有文档
     * @param index 词典的类型
     * @return
     */
    public String queryNoScore(String index){
        String documents=this.queryNoScore(index,MAX_SIZE_FILTER_BY_INDEX);
        return documents;
    }

    /**
     * 读取index下的所有文档
     * @param index 词典的类型
     * @param size 返回多少条结果
     * @return
     */
    public String queryNoScore(String index,int size){
        String url=ES_URL+"/"+index+"/_search";
        String message="{\"size\":"+size+"}";
        String documents= HttpRequestUtils.doPost(url,message);
        return documents;
    }

    /**
     * 读取index-type下的所有文档
     * @param index 词典的类型
     * @return
     */
    public String queryNoScore(String index,String type){
        String documents=this.queryNoScore(index,type,MAX_SIZE_FILTER_BY_INDEX_AND_TYPE);
        return documents;
    }

    /**
     * 读取index-type下的所有文档
     * @param index 词典的类型
     * @param size 返回多少条结果
     * @return
     */
    public String queryNoScore(String index,String type,int size){
        String url=ES_URL+"/"+index+"/"+type+"/_search";
        String message="{\"size\":"+size+"}";
        String documents= HttpRequestUtils.doPost(url,message);
        return documents;
    }


    /**
     * 查询文档index-type下的文档
     * 文档满足的条件为key-value
     * @param index
     * @param type
     * @param key document中的一个索引key
     * @param value document中的索引key对应的内容
     * @return
     */
    public String queryNoScore(String index,String type,String key,String value){
        String documents=this.queryNoScore(index,type,key,value,MAX_SIZE_FILTER_BY_INDEX_AND_TYPE_AND_KEY);
        return documents;
    }

    /**
     * 查询文档index-type下的文档
     * 文档满足的条件为key-value
     * @param index
     * @param type
     * @param key document中的一个索引key
     * @param value document中的索引key对应的内容
     * @param size 返回多少条结果
     * @return
     */
    public String queryNoScore(String index,String type,String key,String value,int size){
        String url=ES_URL+"/"+index+"/"+type+"/"+"_search?";
        String message="{\"query\":{\"constant_score\":{\"filter\":{\"term\":{\""+key+"\":\""+value+"\"}}}}},\"size\":"+size+"}";
        System.out.println(message);
        String documents=HttpRequestUtils.doPost(url,message);
        return documents;
    }


    /**
     * 查询文档index-type下的文档
     * 文档满足的条件为keys-values
     * @param index
     * @param type
     * @param keys document中的一个索引key
     * @param values document中的索引key对应的内容
     * @return
     */
    public String queryNoScore(String index,String type,String[] keys,String[] values){
        String documents=this.queryNoScore(index,type,keys,values,MAX_SIZE_FILTER_BY_INDEX_AND_TYPE_AND_KEY);
        return documents;
    }

    /**
     * 查询文档index-type下的文档
     * 文档满足的条件为keys-values
     * @param index
     * @param type
     * @param keys document中的一个索引key
     * @param values document中的索引key对应的内容
     * @param size 返回多少条结果
     * @return
     */
    public String queryNoScore(String index,String type,String[] keys,String[] values,int size){
        String url=ES_URL+"/"+index+"/"+type+"/"+"_search?";
        String message="{\"query\":{\"constant_score\":{\"filter\":{\"bool\":{\"must\":[";
        for(int i=0;i<keys.length;i++){
            message=message+"{\"term\":{\""+keys[i]+"\":\""+values[i]+"\"}},";
        }
        message=message.substring(0,message.length()-1)+"]}}}}},{\"size\":"+size+"}";
        System.out.println(message);
        String res=HttpRequestUtils.doPost(url,message);
        return res;
    }

    
    
    /**
     * 查询文档index-type下的文档
     * 文档满足的条件为keys-values
     * @param index
     * @param type
     * @return
     */
    
//    curl -XPOST http://192.168.1.101:9200/index/fulltext/_search  -d'
//    {"query" : { "term" : { "content" : "中国" }}}'
    public String termQuery(String index,String type,String data){
        String message="{\"query\" : { \"term\" : { \"data\" : \"'"+data+"'\" }}}";
        String url=ES_URL+"/"+index+"/"+type+"/"+"_search -d ";
        System.out.println(url);
        String res=HttpRequestUtils.doPost(url,message);
        return res;
    }

    /**
     * 带score的查询
     * @param index 索引
     * @param type 类型
     * @param key 部分匹配的列名
     * @param value 部分匹配的内容
     * @return
     */
    public String queryWithScore(String index,String type,String key,String value,String number){
        String url=ES_URL+"/"+index+"/"+type+"/_search?search_type=dfs_query_then_fetch";
        String message="{\"query\":{\"match\":{\"key\":\"value\"}},\"size\":\"number\"}";
        message=message.replace("key",key).replace("value",value).replace("number", number);
        System.out.println(message);
        String res= HttpRequestUtils.doPost(url,message);
//        System.out.println(res);
        return res;
    }
    
    /**
     * 带score的查询
     * @return
     */
    public String queryWithScore_sql(String sql){
        String url="http://101.43.37.173:9200/_sql";
        String res= HttpRequestUtils.doPost(url,sql);
        System.out.println(res);
        return res;
    }

    /**
     * 带score的查询
     * @param index 索引
     * @param type 类型
     * @param mustKey 完全匹配的列名
     * @param mustValue 完全匹配的内容
     * @param shouldKey 部分匹配的列名
     * @param shouldValue 部分匹配的内容
     * @return
     */
    public String queryWithScore(String index,String type,String mustKey,String mustValue,String shouldKey,String shouldValue,String number) {
        return queryWithScore(index,type,mustKey,mustValue,shouldKey,shouldValue,null,null,number);
    }



    /**
     * 带score的查询
     * @param index 索引
     * @param type 类型
     * @param mustKey 完全匹配的列名
     * @param mustValue 完全匹配的内容
     * @param shouldKey 部分匹配的列名
     * @param shouldValue 部分匹配的内容
     * @param orKeys “或”匹配的列名，目前只能对1列进行“或”匹配
     * @param orValues “或”匹配列的内容，只要完全匹配这个数组中的一个值即可。
     * @return
     */
    public String queryWithScore(String index,String type,String mustKey,String mustValue,String shouldKey,String shouldValue,String orKeys,String orValues,String number) {
        String[] mustKeysArr=new String[1];
        String[] mustValuesArr=new String[1];
        String[] shouldKeysArr=new String[1];
        String[] shouldValuesArr=new String[1];
        String[] orValuesArr=new String[1];
        mustKeysArr[0]=mustKey;
        mustValuesArr[0]=mustValue;
        shouldKeysArr[0]=shouldKey;
        shouldValuesArr[0]=shouldValue;
        orValuesArr[0]=orValues;

        return queryWithScore(index,type,mustKeysArr,mustValuesArr,shouldKeysArr,shouldValuesArr,orKeys,orValuesArr,number);
    }





    /**
     * 带score的查询
     * @param index 索引
     * @param type 类型
     * @param mustKeys 完全匹配的列名
     * @param mustValues 完全匹配的内容
     * @param shouldKeys 部分匹配的列名
     * @param shouldValues 部分匹配的内容
     * @param orKeys “或”匹配的列名，目前只能对1列进行“或”匹配
     * @param orValues “或”匹配列的内容，只要完全匹配这个数组中的一个值即可。
     * @return
     */
    public String queryWithScore(String index,String type,String[] mustKeys,String[] mustValues,String[] shouldKeys,String[] shouldValues,String orKeys,String[] orValues,String number){
        String url=ES_URL+"/"+index+"/"+type+"/_search?search_type=dfs_query_then_fetch";

        //开头
        String message="{\"query\":{\"bool\":{";
        //需要完全匹配或部分匹配
        if(mustKeys!=null || shouldKeys!=null){
            message = message + "\"must\":[";
        }
        //完全匹配
        if(mustKeys!=null) {
            for (int i = 0; i < mustKeys.length; i++) {
                message = message + "{\"match_phrase\":{\"key\":{\"query\":\"value\",\"boost\":2}}},";
                System.out.println(message);
                System.out.println(mustKeys[i]);
                System.out.println(mustValues[i]);
                message = message.replace("key", mustKeys[i]).replace("value", mustValues[i]);
            }
            if (shouldKeys==null) {
                message = message.substring(0, message.length() - 1) + "]";
            }
        }
        //部分匹配
        if(shouldKeys!=null){
            for(int i=0;i<shouldKeys.length;i++){
                message=message+"{\"match\":{\"key\":{\"query\":\"value\",\"boost\":1}}},";
                message=message.replace("key",shouldKeys[i]).replace("value",shouldValues[i]);
            }
            message = message.substring(0,message.length()-1) + "]";
        }
        //“或”匹配
        if(!(orKeys==null || orKeys.equals(""))){
            message = message + ",\"should\":[";
            for(int i=0;i<orValues.length;i++){
                message=message+"{\"match_phrase\":{\"key\":{\"query\":\"value\",\"boost\":1}}},";
                message=message.replace("key",orKeys).replace("value",orValues[i]);
            }
            message = message.substring(0,message.length()-1) + "]";
        }
        //最后，加上尾巴
        message=message+"}}},{\"size\":" + number + "}";
//        System.out.println(message);
        String res=HttpRequestUtils.doPost(url,message);
//        System.out.println(res);
        return res;
    }

    /**
     * 带score的查询
     * @param index 索引
     * @param type 类型
     * @param mustKey 完全匹配的列名
     * @param mustValue 完全匹配的内容
     * @param shouldKey 部分匹配的列名
     * @param shouldValue 部分匹配的内容
     * @return
     */
    public String queryWithScore(String index,String type,String mustKey,String mustValue,String shouldKey,String shouldValue) {
    	return queryWithScore(index, type, mustKey, mustValue, shouldKey, shouldValue, "10");
    }



 



    public static void main(String[] args){
        ElasticSearchIO ES=new ElasticSearchIO();
        //新建索引
        try {
        	String out = ES.queryWithScore_sql("SELECT DISTINCT username FROM user order by age desc limit 5");
        	System.out.println(out);
//        	String data = FileUtil.ReadFile("C:\\esdata\\function_druges.json");
//        	ES.insert("function_druges","es_search",data);
//        	ES.queryNoScore("nanastest","my_search");
//          //带相关度分值的查询
//            String mustKeys="name";
//            String mustValues="小";
//        	 String res1=ES.queryWithScore("estest","estest","question,answer","aaaa","5");
//        	 System.out.println(res1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        
//        //新建一个type并插入一条document
//        ES.insert("estest","estest","{\"question\":\"a\",\"answer\":\"bbbb\"}");
//        ES.insert("estest","estest","{\"question\":\"aa\",\"answer\":\"bbbb\"}");
//        ES.insert("estest","estest","{\"question\":\"aaa\",\"answer\":\"bbbb\"}");
//        ES.insert("estest","estest","{\"question\":\"aaaaaa\",\"answer\":\"bbbb\"}");
//        ES.insert("estest","estest","{\"question\":\"111\",\"answer\":\"222\"}");
//        ES.insert("estest","estest","{\"question\":\"ccc\",\"answer\":\"ddd\"}");
//        ES.insert("estest","estest","{\"question\":\"222\",\"answer\":\"aaaa\"}");
//        ES.insert("estest","estest","{\"question\":\"1\",\"answer\":\"111\"}");
//        ES.insert("estest","estest","{\"question\":\"bbbb\",\"answer\":\"ccc\"}");
//        ES.insert("estest","estest","{\"question\":\"ddd\",\"answer\":\"1\"}");
//        //插入一条document
//        ES.insert("robot","doctor_test","{\"question\":\"cccc\",\"answer\":\"dddd\"}");
//        //根据id删除一条document
//        ES.delete("robot","doctor_test","AWaj48x6wbO7T3DD5MCV");
//        //带相关度分值的查询
//        String mustKeys="data";
//        String mustValues="宋";
//        String shouldKeys="question";
//        String shouldValues="五";
//        String res1=ES.queryWithScore("robot","knowledge_luyan",mustKeys,mustValues);
//        String res2=ES.queryWithScore("robot","knowledge_luyan",mustKeys,mustValues,shouldKeys,shouldValues,"10");

}
    
}
