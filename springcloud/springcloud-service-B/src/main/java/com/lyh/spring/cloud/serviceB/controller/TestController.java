package com.lyh.spring.cloud.serviceB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2020/5/18.
 */
@RestController
public class TestController {

    @Autowired
    private  ServiceAFeginClient serviceAFeginClient;

    @RequestMapping("/call")
    public String call(){
        String result=serviceAFeginClient.hello();
        return "b to a 访问结果 -----"+result;

    }
}
