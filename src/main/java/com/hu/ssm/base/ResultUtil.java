package com.hu.ssm.base;

/**
 * json返回值统一格式化工具类
 * */
public class ResultUtil {

    /**
     * 数据交互成功返回
     * */
    public static Result success(Object object){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),object);
    }

    /**
     * 数据交互成功返回
     * */
    public static Result success(Object object,String msg){
        return new Result(ResultEnum.SUCCESS.getCode(), msg,object);
    }

    public static Result success(){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg());
    }

    /**
     * 参数异常
     * */
    public static Result parameterError(String errMsg){
        return new Result(ResultEnum.PARAMETER_ERROR.getCode(),ResultEnum.PARAMETER_ERROR.getMsg()+": "+errMsg,"");
    }

    /**
     * 业务异常
     * */
    public static Result businessError(String message){
        return new Result(ResultEnum.SERVICE_ERROR.getCode(),message,"");
    }

    /**
     * 系统异常
     * */
    public static Result serverError(){
        return new Result(ResultEnum.SERVICE_ERROR.getCode(),ResultEnum.SERVICE_ERROR.getMsg(),"");
    }

    /**
     * 账号未登录
     * */
    public static Result noLogin(){
        return new Result(ResultEnum.NO_LOGIN.getCode(),ResultEnum.NO_LOGIN.getMsg());
    }
}