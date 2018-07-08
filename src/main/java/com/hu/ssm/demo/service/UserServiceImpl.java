package com.hu.ssm.demo.service;

import com.hu.ssm.base.Result;
import com.hu.ssm.base.ResultUtil;
import com.hu.ssm.base.domain.SysUser;
import com.hu.ssm.base.domain.SysUserExample;
import com.hu.ssm.base.mapper.SysUserMapper;
import com.hu.ssm.core.Lock;
import com.hu.ssm.core.RedisDistributedLockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hutiantian
 * @create 2018/5/8 15:19
 * @since 1.0.0
 */
@Transactional
@Service
@Slf4j
public class UserServiceImpl {

    @Resource
    SysUserMapper sysUserMapper;

    public Result findUser(long id){
        Lock lock = new Lock();
        lock.setName("testLock");
        lock.setValue("hutiantian");
        if(!RedisDistributedLockHandler.tryLock(lock)){
            log.error("锁被占用，请重试！");
            return ResultUtil.businessError("锁被占用！");
        }
        SysUserExample userExample = new SysUserExample();
        userExample.or().andIdEqualTo(id);
        List<SysUser> list = sysUserMapper.selectByExample(userExample);
        if(list!=null&&list.size()>0){
            return ResultUtil.success(list.get(0));
        }
        return ResultUtil.businessError("没有找到该用户！");
    }


}
