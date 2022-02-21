package com.example.demo.constant;

/**
 * @Description: 权限相关常量定义
 * @author: rengb
 * @time: 2021/1/5 18:27
 */
public interface AuthConstant {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 用户处理中心client_id
     */
    String SECURITY_CENTER_CLIENT_ID = "security-center";

    /**
     * 用户处理中心接口路径匹配
     */
    String SECURITY_CENTER_URL_PATTERN = "/security-center/**";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * Redis缓存字典信息
     */
    String DIC_MAP_KEY = "dicMap";

    /**
     * Redis缓存组织机构参数配置信息
     */
    String HOSPITAL_SET_MAP_KEY = "hospitalSetMap";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 医院id Http请求头
     */
    String HOSPITAL_ID_HEADER = "hospitalId";

    /**
     * 系统id Http请求头
     */
    String SOFTWARE_ID_HEADER = "softwareId";
    /**
     * 超级管理员角色id
     */
    Long SUPPER_ADMIN = 1l ;
    /**
     * 数据权限过滤sql key
     */
    String DATAAUTH_FILTER_SQL = "dataAuthFilter";
    /**
     * 网关UUID
     */
    String UUID = "UUID";
    String SESSION_ID = "SESSIONID";
    /**
     * Token
     */
    String ASSESS_TOKEN = "token";
    /**
     * Token
     */
    String REFRESH_TOKEN = "refreshToken";
}