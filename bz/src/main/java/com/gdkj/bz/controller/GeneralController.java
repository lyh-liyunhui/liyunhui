package com.gdkj.bz.controller;

import com.gdkj.bz.response.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DELL on 2019/11/22.
 */
/*
* 通用控制器
* 存放页面跳转
* */

 @Controller
 @RequestMapping("/general")
 @CrossOrigin(allowCredentials ="true",allowedHeaders ="*")
public class GeneralController {
    /*
   * 登录页面
   * */
    @RequestMapping(value = "/login")
    public String login(){return CommonReturnType.success("login");}
    /*验证码页面*/
    @RequestMapping(value="getotp")
    public String getotp(){return CommonReturnType.success("getotp");}
    /*注册页面*/
    @RequestMapping(value = "register")
    public String register(){
        return CommonReturnType.success("register");
    }
    /*商品发布页面*/
    @RequestMapping(value = "releasegoods")
    public String releasegoods(){
        return  CommonReturnType.success("releasegoods");
    }
    /*商品详情页*/
    @RequestMapping(value = "goodsdetails")
    public String goodsdetails(){
        return CommonReturnType.success("goodsdetails");
    }
    /*购物车页面*/
    @RequestMapping(value = "shoppingcart")
    public String shoppingcart(){return CommonReturnType.success("shoppingcart");}
    /*
    * 后台页面
    * */
    @RequestMapping(value = "htindex")
    public String htindex(){return CommonReturnType.success("ht/htindex");}
    /*
    * 我的商品
    * */
    @RequestMapping(value = "mygoods")
    public String htrelasegoods(){return CommonReturnType.success("ht/mygoods");}
    /*我的订单*/
    @RequestMapping(value = "myorder")
    public String myorder(){return CommonReturnType.success("ht/myorder");}
    /*用户列表*/
    @RequestMapping(value = "userlist")
    public String userlist(){return CommonReturnType.success("ht/userlist");}
    /*商品列表*/
    @RequestMapping(value = "goodslist")
    public String goodslist(){return CommonReturnType.success("ht/goodslist");}
    /*订单列表*/
    @RequestMapping(value = "orderlist")
    public String orderlist(){return CommonReturnType.success("ht/orderlist");}
    @RequestMapping(value = "404")
    public String error(){
        return  CommonReturnType.success("404");
    }
    @RequestMapping(value = "403")
    public String error1(){
        return  CommonReturnType.success("403");
    }
    @RequestMapping(value = "pin")
    public String personinformation(){
        return  CommonReturnType.success("ht/pin");
    }
}
