package com.hu.ssm.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hutiantian
 * @create 2018/5/16 11:50
 * @since 1.0.0
 */
@Api(tags = "欢迎界面")
@RestController
@RequestMapping("/")
public class WelcomeController {

    @ApiOperation(value = "hello", notes = "hello", produces = MediaType.ALL_VALUE)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "hello,天天";
    }

    @ApiOperation(value = "redis管理session", notes = "redis管理session", produces = MediaType.ALL_VALUE)
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String session(HttpServletRequest request) {
        return "session id 是："+request.getSession().getId();
    }

}

