package com.hu.ssm.core;

import com.hu.ssm.base.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

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
    @Pointcut(value = "execution(* com.hu.ssm.demo..*.*(..,org.springframework.validation.BindingResult,..))")
    public void point() {}


    @Around(value = "point()")
    public Object aspect(ProceedingJoinPoint pjp) throws Throwable{
        log.debug("进入ExceptionAspect------参数切面方法！");
        Object[] objArray = pjp.getArgs();       //获取待加强方法的形参
        BindingResult bindingResult = null;
        for(Object obj:objArray){
            if(obj instanceof BindingResult){
                bindingResult = (BindingResult)obj;
                break;
            }
        }
        if(bindingResult.hasErrors()) {
            return ResultUtil.businessError(getValidateError(bindingResult));
        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Exception e) {
            log.error("待加强的方法执行异常"+pjp.getTarget().getClass().getName(),e);
            return null;
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
