package com.lyh.spring.cloud.serviceA.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2020/5/18.
 */
@RestController

public class TestController  {
    /*@Value("${server.port}")
    private  String port;*/

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    String fallback(){
        return "服务器繁忙";
    }

    /*@Value("${name}")
    private String name;
    @RequestMapping("/hello")
    String hello1(){
    return name;
    }*/
}
