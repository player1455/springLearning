package com.zyc.springframework.AOP;

import com.zyc.springframework.Pojo.Result;
import com.zyc.springframework.anno.RepeatLimit;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class RepeatLimitAop {

    private final StringRedisTemplate stringRedisTemplate;

    @Around("@annotation(com.zyc.springframework.anno.RepeatLimit)")
    public Object repeatLimit(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        RepeatLimit repeatLimit = signature.getMethod().getAnnotation(RepeatLimit.class);
        int expire = repeatLimit.expireSeconds();

        String key = "repeat:test:only:"
                + pjp.getTarget().getClass().getName()
                + ":" + signature.getMethod().getName();


        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(key, "1", expire, TimeUnit.SECONDS);

        System.out.println("加锁结果：" + success); // 看日志

        // 只有【加锁失败】才报错（重复提交）
        if (Boolean.FALSE.equals(success)) {
            return Result.error("操作频繁，请稍后再试");
            //throw new RuntimeException("操作频繁，请稍后再试");
        }

        // 加锁成功 → 放行
        return pjp.proceed();
    }
}