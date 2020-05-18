package com.lyh.spring.cloud.serviceA.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2020/5/18.
 */
@RestController
public class TestController  {
    @Value("${server.port}")
    private  String port;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world"+"端口"+port;
    }
}
