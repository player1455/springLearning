package com.zyc.springframework.AOP;


import com.zyc.springframework.Pojo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class GlobalExceptionHandingAop {


    @Around("@annotation(com.zyc.springframework.anno.GlobalExceptionHanding)")
    public Object GlobalExceptionHanding(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();

        } catch (Exception e) {
            // 捕获所有异常
            log.error("异常信息：{}", e.getMessage());
            return Result.error(e.getMessage());

        } catch (Throwable e) {
            // 捕获 Error（栈溢出、虚拟机错误等）
            log.error("系统严重错误：{}", e.getMessage());
            return Result.error("服务器异常");
        }
    }

}
