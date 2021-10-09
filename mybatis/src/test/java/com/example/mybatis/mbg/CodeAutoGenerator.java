package com.example.mybatis.mbg;

/**
 * @ClassName: CodeAutoGenerator
 * @Description:
 * @Author songxl
 * @Date 2021/6/28
 * @Version 1.0
 */
public class CodeAutoGenerator {

    /**
     * 数据库
     */
    private static String username = "sxl";
    private static String password = "sxl123456";
    private static String url = "jdbc:mysql://127.0.0.1:3306/mall?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

    /**
     * 表名
     */
    private static String[] TABLES = {
            "cms_help"
    };

    /**
     * 生成的实体类忽略表前缀: 不需要则置空
     */
    private static String[] ENTITY_IGNORE_PREFIX = { "sys_" };

    /**
     * 初次生成：entity.java、mapper.java、mapper.xml、baseMapper.xml、service.java、serviceImpl.java、facade.java
     * 再次生成：entity.java、baseMapper.xml、service.java、serviceImpl.java
     *
     * @param args
     */
    public static void main(String[] args) {

        GeneratorUtil.create(username, password, url, TABLES, ENTITY_IGNORE_PREFIX);
    }

}