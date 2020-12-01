package com.lyh.web;

import com.lyh.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2020/11/25.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
//        int i=9/0;
//        String blog=null;
//        if(blog==null){
//            throw new NotFoundException("博客不存在");
//
//        }
        System.out.println("-----index-----");
        return "index";
    }
}
