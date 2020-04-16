package com.miaoshaTest.service;

import com.miaoshaTest.error.BusinessException;
import com.miaoshaTest.service.model.OrderModel;

/**
 * Created by DELL on 2019/9/21.
 */
public interface OrderService {
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws BusinessException;

}
