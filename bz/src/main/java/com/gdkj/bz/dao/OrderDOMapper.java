package com.gdkj.bz.dao;

import com.gdkj.bz.entity.OrderDO;

import java.util.List;

public interface OrderDOMapper {
    int deleteByPrimaryKey(String id);

    int deleteByGoodsId(Integer goodsId);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String id);

    List<OrderDO>selectByUserId(Integer userId);

    List<OrderDO>selectAllorder();

    List<OrderDO>selectByGoodsId(Integer goodsId);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}