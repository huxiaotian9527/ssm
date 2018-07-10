package com.hu.ssm.demo.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author hutiantian
 * @date: 2018/7/10 16:30
 * @since 1.0.0
 */
@Data
public class FileDto {
    @NotEmpty(message="文件名称不能为空！")
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "用户id")
    @NotNull(message="用户id不能为空！")
    private Long userId;
    @ApiModelProperty(value = "年龄")
    @Max(value=120,message="年龄最大不能查过120")
    private int ege;
    @ApiModelProperty(value = "文件")
    private MultipartFile file;
}
