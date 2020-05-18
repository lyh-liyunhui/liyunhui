package com.lyh.spring.cloud.serviceB.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DELL on 2020/5/18.
 */
@FeignClient("SERVICE-A")
public interface ServiceAFeginClient {

    @RequestMapping("/hello")
    public String hello();
}
