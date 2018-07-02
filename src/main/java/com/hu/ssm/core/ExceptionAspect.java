package com.hu.ssm.core;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hutiantian
 * @create 2018/6/29 17:03
 * @since 1.0.0
 */
@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    /**
     * 定义切点: *Controller 且参数中有 BaseReqModel
     */
    @Pointcut(value = "execution(* com.hu.ssm.demo..*.*(..))")
    public void point() {}


    @Around(value = "point()")
    public Object aspect(ProceedingJoinPoint pjp) throws Throwable{
//        Object obj = pjp.getArgs();       获取待加强方法的形参
        log.debug("开始执行切面方法---------");
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            log.error("待加强的方法执行异常"+pjp.getTarget().getClass().getName(),e);
            return null;
        }
        log.debug("结束执行切面方法---------");
        return result;
    }


}
