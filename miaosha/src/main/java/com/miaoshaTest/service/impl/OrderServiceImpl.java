package com.miaoshaTest.service.impl;

import com.miaoshaTest.dao.OrderDOMapper;
import com.miaoshaTest.dao.SequenceDOMapper;
import com.miaoshaTest.dataobject.OrderDO;
import com.miaoshaTest.error.BusinessException;
import com.miaoshaTest.error.EmBusinessError;
import com.miaoshaTest.service.ItemService;
import com.miaoshaTest.service.OrderService;
import com.miaoshaTest.service.SequenceService;
import com.miaoshaTest.service.UserService;
import com.miaoshaTest.service.model.ItemModel;
import com.miaoshaTest.service.model.OrderModel;
import com.miaoshaTest.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by DELL on 2019/9/21.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceService sequenceService;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        //1.校验下单状态，下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "数量信息不存在");
        }
        //2.落单减库存
        boolean result = itemService.decreaseStock(itemId, amount);
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);

        }
        //3.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderprice(itemModel.getPrice().multiply(new BigDecimal(amount)));

        //生成交易流水号
        orderModel.setId(sequenceService.generateOrderNO());
        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        itemService.increaseSales(itemId, amount);
        //返回前端

        return orderModel;
    }

    private OrderDO convertFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderprice().doubleValue());
        return orderDO;
    }
}
