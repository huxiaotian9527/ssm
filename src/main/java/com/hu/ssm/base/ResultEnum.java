package com.hu.ssm.base;

public enum ResultEnum {
    /**访问成功返回**/
    SUCCESS(0,"操作成功"),

    /**数据不存在返回**/
    NOT_FOUND(-1,"notFound [数据不存在 或者 数据为空]"),

    /**异常返回**/
    ERROR(-2,"error [未知异常]"),

    /**服务异常返回**/
    SERVICE_DIS_ERROR(-3,"服务已断开"),

    /**服务异常返回**/
    SERVICE_ERROR(-5,"服务异常"),

    /**参数有异常返回**/
    PARAMETER_ERROR(-4,"parameter error [参数异常:参数为空或者参数类型不符]"),

    /**账号未登录**/
    NO_LOGIN(401,"账号未登录");

    private Integer code;

    private String msg;

    ResultEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
