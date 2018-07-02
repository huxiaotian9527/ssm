package com.hu.ssm.base;

public enum ResultEnum {
    /**访问成功返回**/
    SUCCESS(0,"操作成功！"),

    /**请求参数有异常返回**/
    PARAMETER_ERROR(-1,"请求参数异常！"),

    /**服务异常返回**/
    SERVICE_ERROR(-2,"服务未知异常！"),

    /**账号未登录**/
    NO_LOGIN(-3,"账号未登录！");

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
