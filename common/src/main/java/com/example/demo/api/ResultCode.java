package com.example.demo.api;

/**
 * @Description: 枚举了一些常用API操作码
 * @author: rengb
 * @time: 2021/1/5 18:27
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
//    OK(0, "操作成功"),
//    FAIL(00000001, "操作失败"),
//    RPC_ERROR(00000002, "远程调度失败"),
//    CHECK_CONNECTION(00000005, "是否解除绑定"),
//    SAVE_SUCCESSFUL(00000007, "添加成功"),
//    ALTER_SUCCESSFUL(00000009, "修改成功"),
//    PARAM_ERROR(00029999, "%s"),
//    NOT_EXISTS(00020001, "该数据不存在!"),
//    INSERT_DATA_FAILED(00020002, "数据库写入失败!"),
//    UPDATE_INFO_FAIL(00020003, "更新数据失败!"),
//    PARAM_IS_NULL(0002000, "传入的参数为空!"),
//    PARAM_IS_ERROR(00020005, "传入的参数为错误!"),
//    STATUS_IS_ERROR(00020006, "参数状态错误!"),
    SERVER_IS_ERROR(00020007, "各自业务错误!");
//    NO_PERMISSION(00020008, "无权访问!"),
//    IS_EXISTS(00020009, "已存在!"),
//    ANALYZER_TOKEN_FAIL(10020010, "解析token失败"),
//    TOKEN_PAST(10020011, "token已失效，请重新登录"),
//    SECURITYCODE_ERROR(20020001, "防伪码错误"),
//    APPKEY_ERROR(20020002, "appkey或secret错误"),
//    PRODUCT_STOP_ERROR(20020003, "该产品已停用"),
//    OVERDUE_ERROR(20020004, "访问权限已过期"),
//    NOPERMISSION_ERROR(20020005, "无权限访问"),
//    SERVICE_STOP_ERROR(20020006, "当前服务已停用"),
//    NOTVALID_ERROR(20020007, "该产品未在有效服务期内，无法使用"),
//    EXPIRE_ERROR(20020008, "该产品已超出有效服务期，无法使用");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}