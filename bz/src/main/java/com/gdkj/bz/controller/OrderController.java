package com.gdkj.bz.controller;

import com.alibaba.fastjson.JSON;
import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.controller.VO.OrderVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.error.BusinessException;
import com.gdkj.bz.response.CommonReturnType;
import com.gdkj.bz.service.OrderService;
import com.gdkj.bz.util.OrderUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by DELL on 2019/11/18.
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials ="true",allowedHeaders ="*")
public class OrderController {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderUtil orderUtil;
   /* *//*
    * 添加一个订单
    * *//*
    @RequestMapping("/addorder")
    public CommonReturnType addOrder(@RequestParam("goodsId")Integer goodsId,
                                     @RequestParam("amount")Integer amount,
                                     @RequestParam("payment")Integer payment,
                                     @RequestParam("status")Integer status
                                     ) throws BusinessException {
        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");
        //封装实体类
        OrderVO orderVO=orderUtil.addOrder(goodsId,amount,payment,status,userVO);

        orderService.addOrder(orderVO);

        return CommonReturnType.create(null);
    }*/

    /*
    * 添加购物车订单
    * */
    @RequiresRoles("user")
    @RequestMapping(value = "/addcartorder")
    @ResponseBody
    public CommonReturnType addcartorder(/*@RequestParam("goodsidlist")List goodsidlist,*/
                                         @RequestParam String list
                                         /*@RequestParam Integer payment*/) throws BusinessException {
        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        List<OrderVO>orderVOList= JSON.parseArray(list,OrderVO.class);

        orderService.addcartOrder(orderVOList,userVO,1);

        System.out.println("发送卖家手机号码"+userVO.getTelphone()+"近期联系前往交易地点：3栋301二手交易中心");
        return CommonReturnType.create(null);
    }

    /*
    * 获取所有的订单
    * */
    @RequiresRoles("admin")
    @RequestMapping(value = "/getAllorder")
    public String getAllorder(Model model){
        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        List<OrderListVO>orderDOList=orderService.getAllorder();

        model.addAttribute("orderlist",orderDOList);

        return CommonReturnType.success("ht/orderlist");
    }

    @RequestMapping("delorder")
    @ResponseBody
    public CommonReturnType delorder(@RequestParam("id") String id){

        orderService.delorder(id);

        return CommonReturnType.create(null);
    }
}
