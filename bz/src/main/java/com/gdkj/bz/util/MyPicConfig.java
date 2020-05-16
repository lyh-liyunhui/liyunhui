package com.gdkj.bz.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by DELL on 2019/11/23.
 */
/*
* 配置静态资源路径
* */
@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/goods_img/**").addResourceLocations("file:D:\\idea\\bz\\src\\main\\resources\\static\\image\\goods_img\\");
        }
}
