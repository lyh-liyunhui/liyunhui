package com.gdkj.bz.service.impl;

import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.controller.VO.OrderVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.dao.CartDOMapper;
import com.gdkj.bz.dao.GoodsDOMapper;
import com.gdkj.bz.dao.InventoryDOMapper;
import com.gdkj.bz.dao.OrderDOMapper;
import com.gdkj.bz.entity.GoodsDO;
import com.gdkj.bz.entity.OrderDO;
import com.gdkj.bz.error.BusinessException;
import com.gdkj.bz.service.OrderService;
import com.gdkj.bz.service.SequenceService;
import com.gdkj.bz.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2019/11/18.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private InventoryDOMapper inventoryDOMapper;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private CartDOMapper cartDOMapper;
    @Autowired
    private GoodsDOMapper goodsDOMapper;
    @Autowired
    private OrderUtil orderUtil;
    @Override
    @Transactional
    public void addcartOrder(List<OrderVO> orderVOList, UserVO userVO, Integer payment) throws BusinessException {
        if(userVO==null){
            System.out.println("用户为空");
        }
        for(OrderVO orderVO:orderVOList){
            if(orderVO.getGoodsId()!=null) {
                OrderDO orderDO=new OrderDO();
                orderDO.setId(sequenceService.generateOrderNO());
                orderDO.setUserId(userVO.getId());
                orderDO.setGoodsId(orderVO.getGoodsId());
                orderDO.setAmount(orderVO.getAmount());
                orderDO.setSubtotal(orderVO.getSubtotal());
                if (payment == 1) {
                    orderDO.setPayment("微信支付");
                } else {
                    orderDO.setPayment("支付宝支付");
                }
                orderDO.setCreatetime(new Date());
               /* //下单先减少库存
                boolean result=inventoryDOMapper.decreaseCount(orderDO.getGoodsId(),orderDO.getAmount());
                if (!result) {
                    throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
                }*/
                //插入订单
                orderDOMapper.insertSelective(orderDO);
                /*//增加销量
                inventoryDOMapper.increaseSales(orderDO.getGoodsId(),orderDO.getAmount());*/

                cartDOMapper.deleteBygoodsId(orderVO.getGoodsId());
            }
        }
        }

    @Override
    public List<OrderListVO> getAllorder() {

        List<OrderDO> orderDOList=orderDOMapper.selectAllorder();

        List<OrderListVO>orderListVOList=new ArrayList<>();
        for (OrderDO orderDO:orderDOList){
            GoodsDO goodsDO=goodsDOMapper.selectByPrimaryKey(orderDO.getGoodsId());

            OrderListVO orderListVO=orderUtil.convertFromentity2(goodsDO,orderDO);

            orderListVOList.add(orderListVO);
        }
        return orderListVOList;
    }

    /*
    * 删除订单
    * */
    @Override
    public void delorder(String id) {
        orderDOMapper.deleteByPrimaryKey(id);
    }
    /*
    * 添加一个订单
    * *//*
    @Override
    @Transactional
    public void addOrder(OrderVO orderVO) throws BusinessException {
        if(orderVO==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        OrderDO orderDO=orderUtil.convertFromentity1(orderVO);

        orderDO.setId(sequenceService.generateOrderNO());

        //下单先减少库存
        boolean result=inventoryDOMapper.decreaseCount(orderDO.getGoodsId(),orderDO.getAmount());
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        //插入订单
        orderDOMapper.insertSelective(orderDO);
        //增加销量
        inventoryDOMapper.increaseSales(orderDO.getGoodsId(),orderDO.getAmount());
    }*/

}