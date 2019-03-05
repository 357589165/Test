package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

/**
 * @author 王超 by 2019-03-04
 */
@Aspect
@Configuration
public class LockAspect {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    DefaultRedisScript<Boolean> redisScript;

    @Pointcut("execution(* com.example.demo.web.LockController.*(..))")
    public void lockWebPointcut(){}


    @Around("lockWebPointcut()")
    public Object lockWebAround(ProceedingJoinPoint point) throws Throwable {
        // 数据库中key 全名为redis.limit.user
        String key = "user";
        String limit = "1";

        // 使用编辑的lua 脚本进行结果获取
        Boolean result = redisTemplate.execute(redisScript, Collections.singletonList(key), limit);
        // 防止空指针
        if(result == null)
            result = false;
        return result ? point.proceed() : "访问失败";
    }


}
