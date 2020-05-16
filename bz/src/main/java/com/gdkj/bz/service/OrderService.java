package com.gdkj.bz.service;

import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.controller.VO.OrderVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.error.BusinessException;

import java.util.List;

/**
 * Created by DELL on 2019/11/18.
 */
public interface OrderService {

    /*
    * 添加一个订单
    * */
/*    public void addOrder(OrderVO orderVO) throws BusinessException;*/

    /*
    * 添加购物车订单
    * */
    public void addcartOrder(List<OrderVO> orderVOList, UserVO userVO,Integer payment) throws BusinessException;

    public List<OrderListVO> getAllorder();

    void delorder(String id);
}
