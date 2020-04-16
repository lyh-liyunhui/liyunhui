package com.miaoshaTest.controller;

import com.miaoshaTest.error.BusinessException;
import com.miaoshaTest.error.EmBusinessError;
import com.miaoshaTest.response.CommonReturnType;
import com.miaoshaTest.service.OrderService;
import com.miaoshaTest.service.model.OrderModel;
import com.miaoshaTest.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DELL on 2019/9/21.
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials ="true",allowedHeaders ="*")
public class OrdrController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //封装下单请求
    @RequestMapping(value = "/createorder",method ={RequestMethod.POST},consumes ={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                        @RequestParam(name = "amount")Integer amount) throws BusinessException {

       Boolean islogin=(Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(islogin == null||!islogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户还未登录");
        }

        //获取用户登录信息
        UserModel userModel=(UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel=orderService.createOrder(userModel.getId(),itemId,amount);


        return CommonReturnType.create(null);
    }

}
