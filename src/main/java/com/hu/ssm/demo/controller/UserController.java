package com.hu.ssm.demo.controller;

import com.hu.ssm.base.Result;
import com.hu.ssm.base.ResultUtil;
import com.hu.ssm.demo.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hutiantian
 * @create 2018/5/4 11:05
 * @since 1.0.0
 */
@Api(tags = "用户管理")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @ApiOperation(value = "查询用户", notes = "查询用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="list/{id}",method = RequestMethod.GET)
    public Object findUser(@PathVariable("id")  Long id){
        log.debug("logback记录成功，进入用户管理模块");
        Result result = null;
        try{
            result = userService.findUser(id);
        }catch(Exception e){
            result = ResultUtil.serverError();
            log.error("查询用户失败！",e);
        }
        return result;
    }


}
