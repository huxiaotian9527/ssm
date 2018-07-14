package com.hu.ssm.demo.controller;

import com.hu.ssm.base.Result;
import com.hu.ssm.base.ResultUtil;
import com.hu.ssm.demo.Dto.FileDto;
import com.hu.ssm.util.FtpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

}
