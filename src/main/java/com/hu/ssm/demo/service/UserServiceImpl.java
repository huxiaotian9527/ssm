package com.hu.ssm.demo.service;

import com.hu.ssm.base.Result;
import com.hu.ssm.base.ResultUtil;
import com.hu.ssm.base.domain.SysUser;
import com.hu.ssm.base.domain.SysUserExample;
import com.hu.ssm.base.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hutiantian
 * @create 2018/5/8 15:19
 * @since 1.0.0
 */
@Service
public class UserServiceImpl {

    @Resource
    SysUserMapper sysUserMapper;

    public Result findUser(long id){
        SysUserExample userExample = new SysUserExample();
        userExample.or().andIdEqualTo(id);
        List<SysUser> list = sysUserMapper.selectByExample(userExample);
        if(list!=null&&list.size()>0){
            return ResultUtil.success(list.get(0));
        }
        return ResultUtil.businessError("没有找到该用户！");
    }


}
