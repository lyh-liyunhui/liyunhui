package com.lyh.aspect;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/11/25.
 */
@Aspect
@Component
public class LogAspect {
    private  final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.lyh.web.*.*(..))")
    public  void log(){

    }

    @Before("log()")
    /**
     * 前置通知方法
     * 获取url，IP，方法名，参数
     */
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= (HttpServletRequest) attributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] agrs=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,agrs);

        logger.info("Request :{}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("--------doAfter--------------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result){
        logger.info("Result :{}",result);
    }

    private  class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
