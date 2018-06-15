package com.hu.ssm.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *  统一json返回格式
 * */
@Data
@ToString
@AllArgsConstructor
public class Result implements Serializable{
    /*返回码*/
    private Integer code;
    /*返回信息提示*/
    private String message;
    /*返回的数据*/
    private Object data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
