package com.hu.ssm.demo.controller;

import com.hu.ssm.base.Result;
import com.hu.ssm.base.ResultUtil;
import com.hu.ssm.demo.Dto.FileDto;
import com.hu.ssm.util.FtpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hutiantian
 * @date: 2018/7/9 11:04
 * @since 1.0.0
 */
@Api(tags = "ftp测试")
@RestController
@Slf4j
@RequestMapping("/ftp")
public class FtpController {
    @Resource
    private FtpUtil ftpUtil;

    /*
     * 上传文件至本地虚拟机的ftp
     */
    @ApiOperation(value = "上传文件到ftp", notes = "上传文件到ftp", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/fileUpload")
    public Object fileUpload(@Valid @RequestBody FileDto fileDto,BindingResult bindingResult){
        Result result = null;
        try{
            if(bindingResult.hasErrors()) {
                return ResultUtil.businessError(getValidateError(bindingResult));
            }
            long startTime=System.currentTimeMillis();
            log.debug("原始文件目录为："+fileDto.getFile().getOriginalFilename());
            InputStream inputStream = fileDto.getFile().getInputStream();
            if(!ftpUtil.upload(inputStream,"/20180710","1.jpg")){
                result = ResultUtil.businessError("ftp上传失败！");
            }
            long endTime=System.currentTimeMillis();
            log.debug("上传文件所用时间为："+String.valueOf(endTime-startTime)+"ms");
            result = ResultUtil.success();
        }catch(Exception e){
            result = ResultUtil.serverError();
            log.error("查询用户失败！",e);
        }
        return result;
    }

    private String getValidateError(BindingResult result) {
        StringBuilder builder = new StringBuilder();
        result.getFieldErrors().forEach(e -> builder.append(", ").append(e.getField()).append("->").append(e.getDefaultMessage()));
        String res = null;
        if (builder.toString().length() > 2) {
            res = builder.toString().substring(2);
        }
        return res;
    }
}
