package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author : pp
 * @date : Created in 2021/7/29 17:26
 */
@Slf4j
@Aspect
public class RepeatAspect {

    @Pointcut("@annotation(com.example.demo.aspect.RepeatCheck)")
    public void repeatRequestMethod(){

    }

    @Before("repeatRequestMethod()")
    public void before(){

    }
}
